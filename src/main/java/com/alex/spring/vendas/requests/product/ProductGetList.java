package com.alex.spring.vendas.requests.product;

import com.alex.spring.vendas.domain.Category;
import com.alex.spring.vendas.domain.Product;

import java.math.BigDecimal;

public class ProductGetList {

    private String name;
    private BigDecimal price;
    private Category category;
    private String description;
    private Long amount;

    public ProductGetList(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.amount = product.getAmount();
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
}