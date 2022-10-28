package com.alex.spring.vendas.requests.product;

import com.alex.spring.vendas.domain.Category;
import com.alex.spring.vendas.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductPost {

    @NotBlank(message = "The field `name` cannot be empty.")
    @Length(min = 3, message = "The field `name` should've minimum three characters.")
    private String name;
    @NotNull(message = "The field `price` cannot be empty.")
    private BigDecimal price;
    private Category category;
    private String description;
    private Long amount;

    public Product newProduct() {
        Product newProduct = new Product();
        newProduct.setName(this.name);
        newProduct.setPrice(this.price);
        newProduct.setCategory(this.category);
        newProduct.setDescription(this.description);
        newProduct.setAmount(this.amount != null ? this.amount : 0);
        newProduct.setCreatedAt(LocalDate.now());
        return newProduct;
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