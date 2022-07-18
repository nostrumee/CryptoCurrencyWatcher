package com.example.cryptocurrencywatcher.service;

import com.example.cryptocurrencywatcher.model.jpa.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class ScheduledTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    private final CurrencyService currencyService;
    private final UserService userService;

    @Autowired
    public ScheduledTask(CurrencyService currencyService, UserService userService) {
        this.currencyService = currencyService;
        this.userService = userService;
    }

    @Scheduled(cron = "1 * * * * *")
    public void logWarn() {
        currencyService.updateCurrencyPrices();
        currencyService.getAllCurrencies().forEach(System.out::println);
        List<User> users = userService.getAllUsers();

        for (User user: users) {
            double userPrice = Double.parseDouble(user.getPrice());
            String symbol = user.getSymbol();
            double currentPrice = Double.parseDouble(currencyService.getCurrencyPrice(symbol));
            double difference = Math.abs((userPrice - currentPrice) / currentPrice * 100);

            if (difference > 1.0) {
                LOGGER.warn("symbol: {}, username: {}, difference: {}", symbol, user.getUsername(), difference);
            }
        }

    }
}
