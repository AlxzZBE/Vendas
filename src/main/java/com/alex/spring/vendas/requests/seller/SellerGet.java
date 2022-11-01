package com.alex.spring.vendas.requests.seller;

import com.alex.spring.vendas.domain.Seller;
import com.alex.spring.vendas.domain.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SellerGet {

    private String name;
    private String code;
    private Integer totalSales;
    private Status status;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate registeredAt;

    public SellerGet(Seller seller) {
        this.name = seller.getName();
        this.code = seller.getCode();
        this.totalSales = seller.getTotalSales();
        this.status = seller.getStatus();
        this.registeredAt = seller.getRegisteredAt();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }
}