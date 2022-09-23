package com.example.gifAndUsdService.clients;

import com.example.gifAndUsdService.models.CurrencyRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "historical", url = "${currency.client.url}")
public interface CurrencyClient {
    @RequestMapping(method = RequestMethod.GET, path = "/{date}.json?app_id=${currency.client.apiKey}&symbols=RUB")
    CurrencyRateResponse getRate(@PathVariable("date") String date);
}