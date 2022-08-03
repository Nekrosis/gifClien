package com.example.demo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "gif", url = "${gif.client.url}")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map> getGif();
}
