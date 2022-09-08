package com.example.demo.service;

import com.example.demo.clients.GifClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifService {
    @Autowired
    private GifClient gifClient;
    @Autowired
    private UsdService usdService;

    public String getGif() {
        usdService.getUSD();
        usdService.setPropertiesGif();
        String jsonpathCreatorURL = "$['data']['images']['original']['url']";
        DocumentContext documentContext = JsonPath.parse(gifClient.getGif());
        return documentContext.read(jsonpathCreatorURL).toString();
    }
}
