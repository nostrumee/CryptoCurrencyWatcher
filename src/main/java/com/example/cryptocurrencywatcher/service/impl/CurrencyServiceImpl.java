package com.example.cryptocurrencywatcher.service.impl;

import com.example.cryptocurrencywatcher.model.dto.CurrencyDto;
import com.example.cryptocurrencywatcher.model.jpa.Currency;
import com.example.cryptocurrencywatcher.repository.CurrencyRepository;
import com.example.cryptocurrencywatcher.service.CoinloreClient;
import com.example.cryptocurrencywatcher.service.CurrencyService;
import com.example.cryptocurrencywatcher.service.exception.CurrencyNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ObjectMapper objectMapper;
    private final CoinloreClient coinloreClient;

    @Value("${accessible-currencies}")
    private String accessibleCurrencies;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ObjectMapper objectMapper, CoinloreClient coinloreClient) {
        this.currencyRepository = currencyRepository;
        this.objectMapper = objectMapper;
        this.coinloreClient = coinloreClient;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public String getCurrencyPrice(String symbol) {
        Currency currency = currencyRepository.findBySymbol(symbol).orElseThrow(CurrencyNotFoundException::new);
        return currency.getPrice();
    }

    @Override
    public void initialize() {
        try {
            List<Currency> currencies = objectMapper.readValue(accessibleCurrencies, new TypeReference<>() {});
            currencyRepository.saveAll(currencies);
            updateCurrencyPrices();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCurrencyPrices() {
        List<Currency> currencies = currencyRepository.findAll();
        List<Integer> currenciesIds = currencies
                .stream()
                .map(Currency::getId)
                .collect(Collectors.toList());

        List<Currency> updatedCurrencies = new ArrayList<>();

        for (Integer id : currenciesIds) {
            CurrencyDto currencyFromAPI = coinloreClient.getCurrency(id);
            String price = currencyFromAPI.getPriceUsd();
            Currency currency = currencyRepository.findById(id).orElseThrow(CurrencyNotFoundException::new);
            currency.setPrice(price);
            updatedCurrencies.add(currency);
        }

        currencyRepository.saveAll(updatedCurrencies);
    }

}