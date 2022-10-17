package com.alex.spring.vendas.domain;

import com.alex.spring.vendas.exceptions.ArgumentNotValidException;

public enum Gender {
    M("Masculino"), F("Feminino");

    Gender(String name) {
        this.name = name;
    }

    private final String name;

    public static Gender newGender(String value) {
        switch (value.toUpperCase()) {
            case "M" -> {
                return Gender.M;
            }
            case "F" -> {
                return Gender.F;
            }
            default -> {
                throw new ArgumentNotValidException("The `%s` value is not one of the values accepted [M, F]."
                        .formatted(value));
            }
        }
    }

    public String getName() {
        return this.name;
    }
}