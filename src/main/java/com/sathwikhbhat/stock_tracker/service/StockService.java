package com.sathwikhbhat.stock_tracker.service;

import com.sathwikhbhat.stock_tracker.client.StockClient;
import com.sathwikhbhat.stock_tracker.dto.AlphaVantageResponse;
import com.sathwikhbhat.stock_tracker.dto.DailyStockResponse;
import com.sathwikhbhat.stock_tracker.dto.StockHistoryResponse;
import com.sathwikhbhat.stock_tracker.dto.StockOverviewResponse;
import com.sathwikhbhat.stock_tracker.dto.StockResponse;
import com.sathwikhbhat.stock_tracker.entity.FavoriteStock;
import com.sathwikhbhat.stock_tracker.repository.FavoriteStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockClient stockClient;

    @Autowired
    private FavoriteStockRepository favoriteStockRepository;

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

    public List<DailyStockResponse> getStockHistoryForSymbol(String stockSymbol, int days) {
        StockHistoryResponse response = stockClient.getStockHistory(stockSymbol, days);
        return response.timeSeries().entrySet().stream()
                .limit(days)
                .map(entry -> new DailyStockResponse(
                        entry.getKey(),
                        Double.parseDouble(entry.getValue().open()),
                        Double.parseDouble(entry.getValue().high()),
                        Double.parseDouble(entry.getValue().low()),
                        Double.parseDouble(entry.getValue().close()),
                        Long.parseLong(entry.getValue().volume())
                ))
                .toList();
    }

    @Transactional
    public FavoriteStock addFavorite(String stockSymbol) {
        if(favoriteStockRepository.existsBySymbol(stockSymbol)) {
            throw new RuntimeException("Favorite Stock already exists");
        }

        FavoriteStock favoriteStock = FavoriteStock.builder()
                .symbol(stockSymbol)
                .build();

        return favoriteStockRepository.save(favoriteStock);
    }

}