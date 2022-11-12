package com.alex.spring.vendas.domain;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gen")
    @SequenceGenerator(name = "id_seq_gen", sequenceName = "id_seq_gen", initialValue = 1)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
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

    public Long getId() {
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