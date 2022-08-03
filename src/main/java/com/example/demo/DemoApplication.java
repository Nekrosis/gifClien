package com.example.demo;

import com.example.demo.service.ConvertService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.demo", "com.example.demo.controller"})
public class DemoApplication {
    public static void main(String[] args) {
        ConvertService service = new ConvertService();
        service.setProperties();
        SpringApplication.run(DemoApplication.class, args);
    }
}
