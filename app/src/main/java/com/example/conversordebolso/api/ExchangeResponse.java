package com.example.conversordebolso.api;

import java.util.Map;

public class ExchangeResponse {
    private String base;
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
