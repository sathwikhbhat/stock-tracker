package com.sathwikhbhat.stock_tracker.exception;

public class StockDataException extends RuntimeException {
    public StockDataException(String stockSymbol) {
        super("Error fetching stock data: " + stockSymbol);
    }
}