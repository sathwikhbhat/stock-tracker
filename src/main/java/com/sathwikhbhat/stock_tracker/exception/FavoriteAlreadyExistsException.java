package com.sathwikhbhat.stock_tracker.exception;

public class FavoriteAlreadyExistsException extends RuntimeException{
    public FavoriteAlreadyExistsException(String symbol) {
        super("Symbol already saved as Favorite: " + symbol);
    }
}