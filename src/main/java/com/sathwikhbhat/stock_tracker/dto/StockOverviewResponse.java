package com.sathwikhbhat.stock_tracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockOverviewResponse(
        @JsonProperty("Symbol") String symbol,
        @JsonProperty("Name") String name,
        @JsonProperty("Description") String description,
        @JsonProperty("Sector") String sector,
        @JsonProperty("Industry") String industry,
        @JsonProperty("MarketCapitalization") String marketCapitalization,
        @JsonProperty("PERatio") String peRatio,
        @JsonProperty("DividendYield") String dividendYield
) {
}