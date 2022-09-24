package com.example.gifAndUsdService.service;

import com.example.gifAndUsdService.clients.CurrencyClient;
import com.example.gifAndUsdService.clients.GifClient;
import com.example.gifAndUsdService.models.CurrencyRateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringBootTest
public abstract class BaseIT {

    @MockBean
    CurrencyClient currencyClient;
    @MockBean
    GifClient gifClient;

    @Autowired
    GifService gifService;

    @Autowired
    UsdService usdService;

    @BeforeEach
    void init() {
        usdService.clearCache();
    }
    void whenRateIs(String date, String rate) {
        var response = CurrencyRateResponse.builder()
                .rates(new CurrencyRateResponse.CurrencyRates(new BigDecimal(rate)))
                .build();
        when(currencyClient.getRate(date)).thenReturn(response);
    }

}