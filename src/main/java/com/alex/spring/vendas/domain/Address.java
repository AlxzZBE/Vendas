package com.alex.spring.vendas.domain;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(nullable = false, length = 8)
    private String postalCode;
    @Column(nullable = false, length = 2)
    private String uf;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false, length = 20)
    private String number;
    private String name;
    private String bairro;
    private String complemento;

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public String getName() {
        return name;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }
}