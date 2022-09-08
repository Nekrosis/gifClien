package com.example.gifAndUsdService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.gifAndUsdService", "com.example.gifAndUsdService.controller"})
public class GifAndUsdServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GifAndUsdServiceApplication.class, args);
    }
}
