package com.alex.spring.vendas.domain;

public enum SaleStatus {
    OPEN("Aberta"), IN_PROGRESS("Em Andamento"),
    DONE("Concluída"), CANCELLED("Cancelada");

    private final String name;

    SaleStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}