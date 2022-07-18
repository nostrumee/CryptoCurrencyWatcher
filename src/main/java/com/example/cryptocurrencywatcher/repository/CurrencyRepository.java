package com.example.cryptocurrencywatcher.repository;

import com.example.cryptocurrencywatcher.model.jpa.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findBySymbol(String symbol);

    Optional<Currency> findById(Integer id);

    List<Currency> findAll();

}
