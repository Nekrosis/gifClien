package com.example.gifAndUsdService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "gif", url = "${gif.client.url}")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET)
    String getGif();
}
