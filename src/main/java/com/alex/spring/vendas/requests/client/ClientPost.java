package com.alex.spring.vendas.requests.client;

import com.alex.spring.vendas.domain.Address;
import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public class ClientPost {

    @NotBlank(message = "The field `name` cannot be empty.")
    @Length(min = 3, message = "The field `name` should've minimum three characters.")
    private String name;
    @NotBlank(message = "The field `gender` should've one character.")
    @Length(min = 1, max = 1, message = "The field `gender` should've only one character -> [M, F].")
    private String gender;
    @NotNull(message = "The field `address` cannot be null.")
    @Size(min = 1, message = "The field `address` should've minimum one address.")
    private List<Address> address;

    public Client newClient() {
        Client newClient = new Client();
        newClient.setName(this.name);
        newClient.setGender(Gender.newGender(gender));
        newClient.setAddress(this.address);
        newClient.setCreatedAt(LocalDate.now());
        return newClient;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public List<Address> getAddress() {
        return address;
    }
}