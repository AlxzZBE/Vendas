package com.alex.spring.vendas.requests.client;

import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Level;
import com.alex.spring.vendas.requests.address.AddressGetList;

import java.time.LocalDate;
import java.util.List;

public class ClientGetOne {

    private String name;
    private String gender;
    private Level level;
    private LocalDate created_at;
    private List<AddressGetList> addressGetList;

    public ClientGetOne(Client client) {
        this.name = client.getName();
        this.gender = client.getGender().getName();
        this.addressGetList = client.getAddress().stream().map(AddressGetList::new).toList();
        this.level = client.getLevel();
        this.created_at = client.getCreatedAt();
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public List<AddressGetList> getAddressGetList() {
        return addressGetList;
    }

    public Level getLevel() {
        return level;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }
}