package com.alex.spring.vendas.requests.saleproduct;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SaleProductPost {

    @NotNull(message = "The field `productId` cannot be null.")
    private Long productId;
    @NotNull(message = "The field `saleId` cannot be null.")
    private Long saleId;
    //@NotNull(message = "The field `amount` cannot be null.")
    private Integer amount = 1;
    private BigDecimal discount = BigDecimal.ZERO;

    public Long getProductId() {
        return productId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}