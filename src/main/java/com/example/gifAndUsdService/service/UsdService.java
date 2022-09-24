package com.example.gifAndUsdService.service;

import com.example.gifAndUsdService.clients.CurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.annotations.VisibleForTesting;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsdService {
    private final Map<String, BigDecimal> cache = new ConcurrentHashMap<>();
    private final Clock clock;
    private final CurrencyClient currencyClient;

    public BigDecimal getToday() {
        var date = LocalDate.now(clock).format(DateTimeFormatter.ISO_DATE);
        return getRate(date);
    }

    public BigDecimal getYesterday() {
        var date = LocalDate.now(clock).minusDays(1).format(DateTimeFormatter.ISO_DATE);
        return getRate(date);
    }

    //for debug only
    public Map<String, BigDecimal> getUSD() {
        getToday();
        getYesterday();
        return cache;
    }

    @TestOnly
    @VisibleForTesting
    void clearCache() {
        cache.clear();
    }

    private BigDecimal getRate(String date) {
        return cache.computeIfAbsent(date, it -> {
            var response = currencyClient.getRate(it);
            var rate = response.getRates().getRub().setScale(6, RoundingMode.HALF_UP);
            log.info("Rate for {} is {}", date, rate);
            return rate;
        });
    }
}

