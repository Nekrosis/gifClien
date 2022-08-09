package com.example.demo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@FeignClient(name = "historical", url = "${feign.client.url.Today}")
public interface USDClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map> getUSD();
}
