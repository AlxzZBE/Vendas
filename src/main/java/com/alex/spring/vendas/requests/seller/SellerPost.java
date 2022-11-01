package com.alex.spring.vendas.requests.seller;

import com.alex.spring.vendas.domain.Seller;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SellerPost {

    @NotBlank(message = "The field `name` cannot be empty.")
    @Length(min = 3, message = "The field `name` should've minimum three characters.")
    private String name;
    @NotBlank(message = "The field `code` cannot be empty.")
    @Length(min = 6, message = "The field `code` should've minimum six characters.")
    private String code;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The field `registeredAt` cannot be null.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate registeredAt;

    public Seller newSeller() {
        Seller newSeller = new Seller();
        newSeller.setName(this.name);
        newSeller.setCode(this.code);
        newSeller.setRegisteredAt(this.registeredAt);
        return newSeller;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }
}