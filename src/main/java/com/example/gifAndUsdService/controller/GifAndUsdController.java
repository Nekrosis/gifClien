package com.example.gifAndUsdService.controller;


import com.example.gifAndUsdService.service.GifService;
import com.example.gifAndUsdService.service.UsdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GifAndUsdController {
    private final GifService gifService;
    private final UsdService usdService;


    @GetMapping(path = "/gif")
    public String getGif(@NotNull Model model) {
        model.addAttribute("gifUrl", gifService.getGif());
        return "index";
    }

    @GetMapping(path = "/usd")
    public ResponseEntity<Map<String, BigDecimal>> getUSD() {
        return ResponseEntity.ok(usdService.getUSD());
    }
}