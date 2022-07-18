package com.example.cryptocurrencywatcher.model.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @Id
    private Integer id;

    private String symbol;

    @JsonProperty("price_usd")
    private String price;

}
