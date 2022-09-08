package com.example.gifAndUsdService;

import com.example.gifAndUsdService.controller.GifAndUsdController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class GifAndUsdServiceApplicationTests {
@Autowired
GifAndUsdController gifAndUsdController;
    @Test
    void contextLoads() {
        assertThat(gifAndUsdController).isNotNull();
    }

}
