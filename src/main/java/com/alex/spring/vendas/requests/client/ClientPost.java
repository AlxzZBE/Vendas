package com.alex.spring.vendas.requests;

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
    @NotNull(message = "The field `gender` should've only one character.")
    private Gender gender;
    @NotNull(message = "The field `address` cannot be null.")
    @Size(min = 1, message = "The field `address` should've minimum one address")
    private List<Address> address;

    public Client newClient() {
        Client newClient = new Client();
        newClient.setName(this.name);
        newClient.setGender(this.gender);
        newClient.setAddress(this.address);
        newClient.setCreated_at(LocalDate.now());
        return newClient;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Address> getAddress() {
        return address;
    }
}