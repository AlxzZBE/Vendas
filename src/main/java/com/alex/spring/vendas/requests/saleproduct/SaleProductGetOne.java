package com.alex.spring.vendas.requests.saleproduct;

import com.alex.spring.vendas.domain.SaleProduct;

import java.math.BigDecimal;

public class SaleProductGetOne {

    private Integer saleId;
    private Integer productId;
    private String productName;
    private String productCategory;
    private Integer amount;
    private BigDecimal unitPrice;
    private BigDecimal discountPrice;
    private BigDecimal totalPrice;

    public SaleProductGetOne(SaleProduct saleProduct) {
        this.saleId = saleProduct.getSale().getId();
        this.productId = saleProduct.getProduct().getId();
        this.productName = saleProduct.getProduct().getName();
        this.productCategory = saleProduct.getProduct().getCategory() != null ?
                saleProduct.getProduct().getCategory().getName() : "N/A";
        this.amount = saleProduct.getAmount();
        this.unitPrice = saleProduct.getUnitPrice();
        this.discountPrice = saleProduct.getDiscountPrice();
        this.totalPrice = saleProduct.getTotalPrice();
    }

    public Integer getSaleId() {
        return saleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
}