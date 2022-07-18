package com.example.cryptocurrencywatcher.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    private Integer id;

    private String symbol;

    private String name;

    @JsonProperty("nameid")
    private String nameId;

    private Integer rank;

    @JsonProperty("price_usd")
    private String priceUsd;

    @JsonProperty("percent_change_24h")
    private String percentChange24h;

    @JsonProperty("percent_change_1h")
    private String percentChange1h;

    @JsonProperty("percent_change_7d")
    private String percentChange7d;

    @JsonProperty("market_cap_usd")
    private String marketCapUsd;

    private String volume24;

    @JsonProperty("volume24_native")
    private String volume24Native;

    @JsonProperty("csupply")
    private String cSupply;

    @JsonProperty("price_btc")
    private String priceBtc;

    @JsonProperty("tsupply")
    private String tSupply;

    @JsonProperty("msupply")
    private String mSupply;

}
