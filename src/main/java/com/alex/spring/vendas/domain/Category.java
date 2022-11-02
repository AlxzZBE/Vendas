package com.alex.spring.vendas.domain;

public enum Category {
    DEFAULT("N/A"), GROCERY("Mercado"), SHOES("Tênis"), CLOTHES("Roupas"),
    ELECTRONICS("Eletrônicos"), PETS("Pets");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}