package com.alex.spring.vendas.requests.product;

import com.alex.spring.vendas.domain.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductGetOne {

    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private Long amount;
    private String imageName;
    private LocalDate createdAt;

    public ProductGetOne(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory().getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.imageName = product.getImageName();
        this.createdAt = product.getCreatedAt();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Long getAmount() {
        return amount;
    }

    public String getImageName() {
        return imageName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}