package com.example.gifAndUsdService.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class GifServiceTest {
    @Autowired
    ApplicationContext context;
    @MockBean
    private GifService gifService;

    @Test
    void getGif() {
        Mockito.when(gifService.getGif())
                .thenReturn("https://media0.giphy.com/media/IwaUqvrMJCklpy1g2M/giphy.gif?cid=9a04831e9b0cbae26559556ab346da96d2d631c8e5f0eb8e&rid=giphy.gif&ct=g");
        GifService service = context.getBean(GifService.class);
        String url = service.getGif();
        Assertions
                .assertEquals("https://media0.giphy.com/media/IwaUqvrMJCklpy1g2M/giphy.gif?cid=9a04831e9b0cbae26559556ab346da96d2d631c8e5f0eb8e&rid=giphy.gif&ct=g", url);
        Mockito.verify(gifService).getGif();
    }
}