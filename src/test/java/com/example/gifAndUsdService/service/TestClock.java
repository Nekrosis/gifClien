package com.example.gifAndUsdService.service;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TestClock extends Clock {
    public static final Instant FIXED_CLOCK_INSTANT = Instant.parse("2019-01-10T00:00:00Z");

    private Instant currentInstant;

    public TestClock(Instant currentInstant) {
        this.currentInstant = currentInstant;
    }

    @Override
    public ZoneId getZone() {
        return ZoneId.of("UTC");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        return currentInstant;
    }

    public void setCurrentInstant(Instant currentInstant) {
        this.currentInstant = currentInstant;
    }
}
