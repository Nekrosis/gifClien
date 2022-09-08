package com.example.demo.service;

import com.example.demo.clients.UsdClientToday;
import com.example.demo.clients.UsdClientYesterday;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@Data
public class UsdService {
    @Autowired
    private UsdClientYesterday yesterday;
    @Autowired
    private UsdClientToday today;
    private final Map<String, Double> rubleToDollarExchangeRate = new HashMap<>();

    public Map<String, Double> getUSD() {
        setPropertiesUsd();
        String jsonpathCreatorURL = "$['rates']['RUB']";
        DocumentContext documentContextToday = JsonPath.parse(today.getUsdToday());
        DocumentContext documentContextYesterday = JsonPath.parse(yesterday.getUsdYesterday());
        Double today = documentContextToday.read(jsonpathCreatorURL);
        Double yesterday = documentContextYesterday.read(jsonpathCreatorURL);
        rubleToDollarExchangeRate.put("Today", today);
        rubleToDollarExchangeRate.put("Yesterday", yesterday);
        return rubleToDollarExchangeRate;
    }

    public void setPropertiesGif() {
        Properties properties = new Properties();
        try {
            InputStream in = new FileInputStream("src/main/resources/application.properties");
            properties.load(in);
            if (rubleToDollarExchangeRate.get("Today") > rubleToDollarExchangeRate.get("Yesterday")) {
                properties.setProperty("gif.client.url", "https://api.giphy.com/v1/gifs/random?api_key=${idGif}&tag=rich&rating=g&bundle=clips_grid_picker");
            } else {
                properties.setProperty("gif.client.url", "https://api.giphy.com/v1/gifs/random?api_key=${idGif}&tag=broke&rating=g&bundle=clips_grid_picker");
            }
            properties.store(new FileOutputStream("src/main/resources/application.properties"), null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPropertiesUsd() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        LocalDate yesterdayDate = localDate.minusDays(1);
        String today = date.format(localDate);
        String yesterday = date.format(yesterdayDate);
        Properties properties = new Properties();
        try {
            InputStream in = new FileInputStream("src/main/resources/application.properties");
            properties.load(in);
            properties.setProperty("feign-client-url.today", "https://openexchangerates.org/api/historical/" + today + ".json?app_id=${idUSD}&symbols=RUB");
            properties.setProperty("feign-client-url.yesterday", "https://openexchangerates.org/api/historical/" + yesterday + ".json?app_id=${idUSD}&symbols=RUB");
            properties.store(new FileOutputStream("src/main/resources/application.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
