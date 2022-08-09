package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertModel {
    @JsonIgnore
    private String disclaimer;
    @JsonIgnore
    private String license;
    @JsonIgnore
    private long timestamp;
    @JsonIgnore
    private String base;
    @JsonProperty("rates")
    private Map<String, Double> rates;

    public void setProperties() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        LocalDate yesterdayDate = localDate.minusDays(1);
        String today = date.format(localDate);
        String yesterday = date.format(yesterdayDate);
        Properties properties = new Properties();
        try {
            InputStream in = new FileInputStream("src/main/resources/application.properties");
            properties.load(in);
            properties.setProperty("feign.client.url.Today", "https://openexchangerates.org/api/historical/" + today + ".json?app_id=${idUSD}&symbols=RUB");
            properties.setProperty("feign.client.url.Yesterday", "https://openexchangerates.org/api/historical/" + yesterday + ".json?app_id=${idUSD}&symbols=RUB");
            properties.store(new FileOutputStream("src/main/resources/application.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
