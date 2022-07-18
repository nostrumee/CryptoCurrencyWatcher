package com.example.cryptocurrencywatcher.service;

import com.example.cryptocurrencywatcher.model.jpa.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrencies();

    String getCurrencyPrice(String symbol);

    void initialize();

    void updateCurrencyPrices();
}
