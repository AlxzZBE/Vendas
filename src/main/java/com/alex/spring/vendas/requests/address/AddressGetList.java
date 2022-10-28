package com.alex.spring.vendas.requests.address;

import com.alex.spring.vendas.domain.Address;

public class AddressGetList {

    private String name;
    private String postalCode;
    private String uf;
    private String city;
    private String street;
    private String number;
    private String bairro;
    private String complemento;

    public AddressGetList(Address address) {
        this.name = address.getName();
        this.postalCode = address.getPostalCode();
        this.uf = address.getUf();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.bairro = address.getBairro();
        this.complemento = address.getComplemento();
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getUf() {
        return uf;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }
}