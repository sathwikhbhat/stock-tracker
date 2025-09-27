package com.sathwikhbhat.stock_tracker.repository;

import com.sathwikhbhat.stock_tracker.entity.FavoriteStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteStockRepository extends JpaRepository<FavoriteStock, Integer> {

    boolean existsBySymbol(String symbol);

}