package com.example.gifAndUsdService;

import com.example.gifAndUsdService.service.TestClock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

import static com.example.gifAndUsdService.service.TestClock.FIXED_CLOCK_INSTANT;

@Configuration
public class TestConfig {

    @Bean
    public Clock clock() {
        return new TestClock(FIXED_CLOCK_INSTANT);
    }

}