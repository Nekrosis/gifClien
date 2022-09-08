package com.example.demo.controller;


import com.example.demo.service.GifService;
import com.example.demo.service.UsdService;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@NoArgsConstructor
public class GifAndUsdController {
    @Autowired
    private GifService gifService;
    @Autowired
    private UsdService usdService;


    @GetMapping(path = "/gif")
    public String getGif(@NotNull Model model) {
        model.addAttribute("gifUrl", gifService.getGif());
        return "index";
    }

    @GetMapping(path = "/usd")
    public ResponseEntity<Map<String, Double>> getUSD() {
        return ResponseEntity.ok(usdService.getUSD());
    }
}