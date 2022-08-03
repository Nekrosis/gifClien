package com.example.demo.controller;

import com.example.demo.service.ConvertService;
import com.example.demo.clients.GifClient;
import com.example.demo.clients.USDClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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
public class FeignController {
    private Map<String, Double> usd = new HashMap<>();
    String urlGif;
    @Value("${feign.client.url.Today}")
    String todayURl;
    @Value("${feign.client.url.Yesterday}")
    String yesterdayURl;
    private final USDClient usdClient;
    @Autowired
    private GifClient gifClient;

    @Autowired
    public FeignController(USDClient usdClient) {
        this.usdClient = usdClient;
    }

    @GetMapping(path = "/gif")
    public String getGif(Model model) {
        Properties properties = new Properties();
        usd.put("Today", convertToJson().get(0));
        usd.put("Yesterday", convertToJson().get(1));
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
        String lol = "$['data']['images']['original']['mp4']";
        DocumentContext documentContext = JsonPath.parse(gifClient.getGif().getBody());
        urlGif = documentContext.read(lol);
        model.addAttribute("url", urlGif);
        System.out.println(model);
        return "index";
    }

    @GetMapping(path = "/USD")
    ResponseEntity<Map> getUSD() {
        return ResponseEntity.ok(usd);
    }

    public List<Double> convertToJson() {
        String[] listURL = {todayURl, yesterdayURl};
        List<Double> list = new ArrayList<>();
        ConvertService convertService;
        RestTemplate restTemplate = new RestTemplate();
        for (String s : listURL) {
            convertService = restTemplate.getForObject(s, ConvertService.class);
            assert convertService != null;
            for (Map.Entry<String, Double> stringDoubleEntry : convertService.getRates().entrySet()) {
                list.add(stringDoubleEntry.getValue());
            }
        }
        return list;
    }
}
