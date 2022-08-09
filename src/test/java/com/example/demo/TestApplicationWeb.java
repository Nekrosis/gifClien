package com.example.demo;

import com.example.demo.controller.FeignController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestApplicationWeb {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    FeignController feignController;

        @Test
    public void shouldReturnGif() throws Exception {
        this.mockMvc.perform(get("/gif")).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void shouldReturnUsd() throws Exception {
        this.mockMvc.perform(get("/USD")).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }
}
