package com.example.gifAndUsdService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateResponse {
    private CurrencyRates rates;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
public static class CurrencyRates{
    @JsonProperty("RUB")
    private BigDecimal rub;
}
}
