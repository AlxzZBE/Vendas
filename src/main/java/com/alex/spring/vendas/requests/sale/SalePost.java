package com.alex.spring.vendas.requests.sale;

import jakarta.validation.constraints.NotNull;

public class SalePost {

    @NotNull(message = "The field `sellerCode` cannot be null.")
    private String sellerCode;
    @NotNull(message = "The field `clientId` cannot be null.")
    private Integer clientId;

    public String getSellerCode() {
        return sellerCode;
    }

    public Integer getClientId() {
        return clientId;
    }
}