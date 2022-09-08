package com.example.gifAndUsdService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "historical", url = "${feign-client-url.today}")
public interface UsdClientToday {
    @RequestMapping(method = RequestMethod.GET)
    String getUsdToday();
}
