package com.example.cryptocurrencywatcher.controller;

import com.example.cryptocurrencywatcher.model.dto.CurrencyDto;
import com.example.cryptocurrencywatcher.model.jpa.Currency;
import com.example.cryptocurrencywatcher.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/currency")
    public String getCurrencyPrice(@RequestParam String symbol) {
        return currencyService.getCurrencyPrice(symbol);
    }
}
