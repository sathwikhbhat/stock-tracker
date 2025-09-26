package com.sathwikhbhat.stock_tracker.service;

import com.sathwikhbhat.stock_tracker.client.StockClient;
import com.sathwikhbhat.stock_tracker.dto.AlphaVantageResponse;
import com.sathwikhbhat.stock_tracker.dto.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockClient stockClient;

    @Autowired
    public StockService(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    public StockResponse getStockForSymbol(String stockSymbol) {
        AlphaVantageResponse response = stockClient.getStockQuote(stockSymbol);
        return StockResponse.builder()
                .symbol(response.globalQuote().symbol())
                .lastUpdated(response.globalQuote().lastTradingDay())
                .build();
    }

}