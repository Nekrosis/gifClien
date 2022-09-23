package com.example.gifAndUsdService.service;

import com.example.gifAndUsdService.clients.GifClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class GifService {

    private final GifClient gifClient;
    private final UsdService usdService;

    public String getGif() {
        if (usdService.getToday().compareTo(usdService.getYesterday()) >= 0) {
            log.info("You are rich");
            return gifClient.getRandomRich().getData().getImages().getOriginal().getUrl();
        } else {
            log.info("You are broke");
            return gifClient.getRandomBroke().getData().getImages().getOriginal().getUrl();
        }
    }

//    private String parse(String response) {
//        String jsonpathCreatorURL = "$['data']['images']['original']['url']";
//        DocumentContext documentContext = JsonPath.parse(response);
//        return documentContext.read(jsonpathCreatorURL).toString();
//    }
}
