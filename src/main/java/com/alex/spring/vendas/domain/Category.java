package com.alex.spring.vendas.domain;

public enum Category {
    GROCERY("Mercado"), SHOES("Tênis"), CLOTHES("Roupas"), ELECTRONICS("Eletrônicos"), PETS("Pets");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}