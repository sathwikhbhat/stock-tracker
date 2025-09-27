package com.sathwikhbhat.stock_tracker.service;

import com.sathwikhbhat.stock_tracker.client.StockClient;
import com.sathwikhbhat.stock_tracker.dto.AlphaVantageResponse;
import com.sathwikhbhat.stock_tracker.dto.StockOverviewResponse;
import com.sathwikhbhat.stock_tracker.dto.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockClient stockClient;

    public StockResponse getStockForSymbol(String stockSymbol) {
        AlphaVantageResponse response = stockClient.getStockQuote(stockSymbol);
        return StockResponse.builder()
                .symbol(response.globalQuote().symbol())
                .price(Double.parseDouble(response.globalQuote().price()))
                .lastUpdated(response.globalQuote().latestTradingDay())
                .build();
    }

    public StockOverviewResponse getStockOverviewForSymbol(String stockSymbol) {
        return stockClient.getStockOverview(stockSymbol);
    }

}