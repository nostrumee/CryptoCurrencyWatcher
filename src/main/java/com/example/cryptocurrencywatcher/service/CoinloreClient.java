package com.example.cryptocurrencywatcher.service;

import com.example.cryptocurrencywatcher.model.dto.CurrencyDto;
import com.example.cryptocurrencywatcher.model.jpa.Currency;
import com.example.cryptocurrencywatcher.repository.CurrencyRepository;
import com.example.cryptocurrencywatcher.service.exception.CurrencyNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CoinloreClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyDto getCurrency(Integer id) {
        String url = "https://api.coinlore.net/api/ticker/?id=";

        try {
            CurrencyDto[] currencyFromAPI = restTemplate.getForObject(new URI(url + id), CurrencyDto[].class);

            if (currencyFromAPI != null) {
                return currencyFromAPI[0];
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
