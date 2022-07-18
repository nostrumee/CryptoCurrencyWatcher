package com.example.cryptocurrencywatcher.model.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private String username;

    private String symbol;

    private String price;
}
