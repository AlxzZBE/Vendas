package com.alex.spring.vendas.requests;

import com.alex.spring.vendas.domain.Address;
import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Level;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class ClientPost {

    private String name;
    private Character gender;
    private List<Address> address;

    public Client newClient() {
        Client newClient = new Client();
        newClient.setName(this.name);
        newClient.setGender(this.gender);
        newClient.setAddress(this.address);
        newClient.setCreated_at(LocalDate.now());
        return newClient;
    }

    @Override
    public String toString() {
        return "ClientPost{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                '}';
    }

    public String getName() {
        return name;
    }

    public Character getGender() {
        return gender;
    }

    public List<Address> getAddress() {
        return address;
    }
}