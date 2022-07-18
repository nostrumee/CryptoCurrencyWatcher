package com.example.cryptocurrencywatcher;

import com.example.cryptocurrencywatcher.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CryptoCurrencyWatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyWatcherApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(CurrencyService currencyService) {
        return (args) -> {
            currencyService.initialize();
        };
    }

}
