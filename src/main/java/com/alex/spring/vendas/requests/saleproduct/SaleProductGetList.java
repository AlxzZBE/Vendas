package com.alex.spring.vendas.requests.saleproduct;

import com.alex.spring.vendas.domain.SaleProduct;

import java.math.BigDecimal;

public class SaleProductGetList {

    private Long saleId;
    private String productName;
    private Integer amount;
    private BigDecimal unitPrice;

    public SaleProductGetList(SaleProduct saleProduct) {
        this.saleId = saleProduct.getSale().getId();
        this.productName = saleProduct.getProduct().getName();
        this.amount = saleProduct.getAmount();
        this.unitPrice = saleProduct.getUnitPrice();
    }

    public Long getSaleId() {
        return saleId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
