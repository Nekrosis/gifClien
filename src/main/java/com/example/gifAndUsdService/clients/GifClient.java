package com.example.gifAndUsdService.clients;

import com.example.gifAndUsdService.models.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "gif", url = "${gif.client.url}")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET,path = "/random?api_key=${gif.client.apiKey}&tag=rich&rating=g&bundle=clips_grid_picker")
    GifResponse getRandomRich();
     @RequestMapping(method = RequestMethod.GET,path ="/random?api_key=${gif.client.apiKey}&tag=broke&rating=g&bundle=clips_grid_picker" )
    GifResponse getRandomBroke();
}
