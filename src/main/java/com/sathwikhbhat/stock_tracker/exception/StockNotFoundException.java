package com.sathwikhbhat.stock_tracker.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String stockSymbol) {
        super("Stock not found: " + stockSymbol);
    }
}