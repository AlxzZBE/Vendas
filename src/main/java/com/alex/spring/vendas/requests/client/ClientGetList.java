package com.alex.spring.vendas.requests.client;

import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Level;

import java.time.LocalDate;

public class ClientGetList {

    private String name;
    private String gender;
    private Level level;
    private LocalDate created_at;

    public ClientGetList(Client client) {
        this.name = client.getName();
        this.gender = client.getGender().getName();
        this.level = client.getLevel();
        this.created_at = client.getCreated_at();
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Level getLevel() {
        return level;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }
}