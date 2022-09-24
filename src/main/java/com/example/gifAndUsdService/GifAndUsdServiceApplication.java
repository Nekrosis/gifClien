package com.example.gifAndUsdService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Clock;


@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.gifAndUsdService", "com.example.gifAndUsdService.controller"})
public class GifAndUsdServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GifAndUsdServiceApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
