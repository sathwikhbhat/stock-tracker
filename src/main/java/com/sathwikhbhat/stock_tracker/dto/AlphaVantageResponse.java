package com.sathwikhbhat.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlphaVantageResponse(@JsonProperty("Global Quote") GlobalQuote globalQuote) {
    public record GlobalQuote(
            @JsonProperty("01. symbol") String symbol,
            @JsonProperty("05. price") String price,
            @JsonProperty("07. last trading day") String lastTradingDay
    ) {
    }
}