package com.alex.spring.vendas.domain;

public enum Gender {
    M("Masculino"), F("Feminino");

    Gender(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return this.name;
    }
}