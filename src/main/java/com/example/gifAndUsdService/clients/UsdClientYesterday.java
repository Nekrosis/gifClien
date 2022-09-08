package com.example.gifAndUsdService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "local", url = "${feign-client-url.yesterday}")
public interface UsdClientYesterday {
    @RequestMapping(method = RequestMethod.GET)
    String getUsdYesterday();
}
