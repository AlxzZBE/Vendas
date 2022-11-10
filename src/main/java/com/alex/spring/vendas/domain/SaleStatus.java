package com.alex.spring.vendas.domain;

import com.alex.spring.vendas.exceptions.ArgumentNotValidException;

public enum SaleStatus {
    OPEN("Aberta"), IN_PROGRESS("Em Andamento"),
    DONE("Concluída"), CANCELLED("Cancelada");

    private final String name;

    SaleStatus(String name) {
        this.name = name;
    }

    public static SaleStatus newSaleStatus(String value) {
        switch (value.toUpperCase()) {
            case "ABERTA" -> {return SaleStatus.OPEN;}
            case "CONCLUIDA" -> {return SaleStatus.DONE;}
            case "EM ANDAMENTO" -> {return SaleStatus.IN_PROGRESS;}
            case "CANCELADA" -> {return SaleStatus.CANCELLED;}
            default -> {
                throw new ArgumentNotValidException(
                        "The `%s` value is not one of the values accepted [ABERTA, CONCLUIDA, EM ANDAMENTO, CANCELADA].".formatted(value));
            }
        }
    }

    public String getName() {
        return name;
    }
}