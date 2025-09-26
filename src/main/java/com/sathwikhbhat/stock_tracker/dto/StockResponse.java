package com.sathwikhbhat.stock_tracker.dto;

import lombok.Builder;

@Builder
public record StockResponse(String symbol, double price, String lastUpdated) {
}