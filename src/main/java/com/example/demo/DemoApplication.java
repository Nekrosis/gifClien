package com.example.demo;

import com.example.demo.model.ConvertModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.demo", "com.example.demo.controller"})
public class DemoApplication {
    public static void main(String[] args) {
        ConvertModel service = new ConvertModel();
        service.setProperties();
        SpringApplication.run(DemoApplication.class, args);
    }
}
