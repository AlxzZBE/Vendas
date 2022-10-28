package com.alex.spring.vendas.requests.product;

import com.alex.spring.vendas.domain.Category;
import com.alex.spring.vendas.domain.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductGetOne {

    private String name;
    private BigDecimal price;
    private Category category;
    private String description;
    private Long amount;
    private String imageName;
    private LocalDate created_at;

    public ProductGetOne(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.imageName = product.getImageName();
        this.created_at = product.getCreatedAt();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
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

    public LocalDate getCreated_at() {
        return created_at;
    }
}