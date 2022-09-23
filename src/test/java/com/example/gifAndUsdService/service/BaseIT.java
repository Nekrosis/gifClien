package com.example.gifAndUsdService.service;

import com.example.gifAndUsdService.clients.CurrencyClient;
import com.example.gifAndUsdService.clients.GifClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

}