package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UsdServiceTest {
    @MockBean
    private UsdService usdService;
    @Autowired
    ApplicationContext context;

    @Test
    void getUsd() {
        Map<String, Double> map = new HashMap<>();
        map.put("Today", 64.2);
        map.put("Yesterday", 61.2);
        Mockito.when(usdService.getUSD()).thenReturn(map);
        UsdService bean = context.getBean(UsdService.class);
        Map<String, Double> usd = bean.getUSD();
        Assertions.assertEquals(map.size(), 2);
        Assertions.assertEquals(usd.size(), 2);
        Assertions.assertEquals(map, usd);
        Assertions.assertEquals(map.get("Today"), usd.get("Today"));
        Assertions.assertEquals(map.get("Yesterday"), usd.get("Yesterday"));
    }
}