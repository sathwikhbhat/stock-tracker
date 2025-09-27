package com.sathwikhbhat.stock_tracker.controller;

import com.sathwikhbhat.stock_tracker.dto.DailyStockResponse;
import com.sathwikhbhat.stock_tracker.dto.FavoriteStockRequest;
import com.sathwikhbhat.stock_tracker.dto.StockOverviewResponse;
import com.sathwikhbhat.stock_tracker.dto.StockResponse;
import com.sathwikhbhat.stock_tracker.entity.FavoriteStock;
import com.sathwikhbhat.stock_tracker.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{stockSymbol}")
    public StockResponse getStock(@PathVariable String stockSymbol) {
        return stockService.getStockForSymbol(stockSymbol.toUpperCase());
    }

    @GetMapping("/{stockSymbol}/overview")
    public StockOverviewResponse getStockOverview(@PathVariable String stockSymbol) {
        return stockService.getStockOverviewForSymbol(stockSymbol.toUpperCase());
    }

    @GetMapping("/{stockSymbol}/history")
    public List<DailyStockResponse> getStockHistory(
            @PathVariable String stockSymbol,
            @RequestParam(defaultValue = "30") int days) {
        return stockService.getStockHistoryForSymbol(stockSymbol.toUpperCase(), days);
    }

    @PostMapping("/favorites")
    public ResponseEntity<FavoriteStock> saveFavoriteStock(@RequestBody FavoriteStockRequest request) {
        FavoriteStock saved = stockService.addFavorite(request.getSymbol().toUpperCase());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/favorites")
    public List<StockResponse> getFavoriteStocks() {
        return stockService.getAllFavorites();
    }

}