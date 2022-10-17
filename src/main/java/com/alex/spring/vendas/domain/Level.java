package com.alex.spring.vendas.domain;

import com.alex.spring.vendas.exceptions.ArgumentNotValidException;

public enum Level {
    BRONZE, SILVER, GOLD, PLATINUM;

    public static Level newLevel(String value) {
        switch (value.toUpperCase()) {
            case "BRONZE" -> {return Level.BRONZE;}
            case "SILVER" -> {return Level.SILVER;}
            case "GOLD" -> {return Level.GOLD;}
            case "PLATINUM" -> {return Level.PLATINUM;}
            default -> {throw new ArgumentNotValidException(
                    "The `%s` value is not one of the values accepted [BRONZE, SILVER, GOLD, PLATINUM].".formatted(value));
            }
        }
    }
}