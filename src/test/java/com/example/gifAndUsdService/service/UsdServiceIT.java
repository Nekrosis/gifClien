package com.example.gifAndUsdService.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UsdServiceIT extends BaseIT {

    private static final String TODAY_DATE = "2019-01-10";
    private static final String YESTERDAY_DATE = "2019-01-09";

    @Test
    void shouldReturnCorrectRateForToday() {
        whenRateIs(TODAY_DATE, "60.000000");
        whenRateIs(YESTERDAY_DATE, "65.000000");
        var actual = usdService.getToday();
        assertThat(actual).isEqualTo("60.000000");
    }

    @Test
    void shouldReturnCorrectRateForYesterday() {
        whenRateIs(TODAY_DATE, "60.000000");
        whenRateIs(YESTERDAY_DATE, "65.000000");
        var actual = usdService.getYesterday();
        assertThat(actual).isEqualTo("65.000000");
    }
}