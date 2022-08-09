package com.example.demo.controller;

import com.example.demo.clients.USDClient;
import com.example.demo.model.ConvertModel;
import com.example.demo.clients.GifClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

@Controller
@NoArgsConstructor
public class FeignController {
    private Map<String, Double> usd = new HashMap<>();
    @Value("${feign.client.url.Today}")
    private String todayURl;
    @Value("${feign.client.url.Yesterday}")
    private String yesterdayURl;
    @Autowired
    private GifClient gifClient;
    @Autowired
    private USDClient usdClient;


    @GetMapping(path = "/gif")
    public String getGif(Model model) {
        convertToJson();
        Properties properties = new Properties();
        try {
            InputStream in = new FileInputStream("src/main/resources/application.properties");
            properties.load(in);
            if (usd.get("Today") > usd.get("Yesterday")) {
                properties.setProperty("gif.client.url", "https://api.giphy.com/v1/gifs/random?api_key=${idGif}&tag=rich&rating=g&bundle=clips_grid_picker");
            } else {
                properties.setProperty("gif.client.url", "https://api.giphy.com/v1/gifs/random?api_key=${idGif}&tag=broke&rating=g&bundle=clips_grid_picker");
            }
            properties.store(new FileOutputStream("src/main/resources/application.properties"), null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String jsonpathCreatorURL = "$['data']['images']['original']['url']";
        DocumentContext documentContext = JsonPath.parse(gifClient.getGif().getBody());
        String GifURL = documentContext.read(jsonpathCreatorURL);
        model.addAttribute("url", GifURL);
        return "index";
    }

    @GetMapping(path = "/USD")
    public ResponseEntity<Map> getUSD() {
        usdClient.getUSD().getBody();
        convertToJson();
        return ResponseEntity.ok(usd);
    }

    public Map<String, Double> convertToJson() {
        String[] listURL = {todayURl, yesterdayURl};
        String[] date = {"Today", "Yesterday"};
        int count = 0;
        ConvertModel convertModel;
        RestTemplate restTemplate = new RestTemplate();
        for (String url : listURL) {
            convertModel = restTemplate.getForObject(url, ConvertModel.class);
            assert convertModel != null;
            for (Map.Entry<String, Double> stringDoubleEntry : convertModel.getRates().entrySet()) {
                usd.put(date[count], stringDoubleEntry.getValue());
                count++;
            }
        }
        return usd;
    }
}