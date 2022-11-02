package com.alex.spring.vendas.requests.sale;

import com.alex.spring.vendas.domain.Sale;
import com.alex.spring.vendas.domain.SaleProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleGetOne {

    private Integer id;
    private String sellerCode;
    private String sellerName;
    private Integer clientId;
    private String clientName;
    private BigDecimal total;
    private BigDecimal discountTotal;
    private String saleStatus;
    private List<SaleProduct> saleProducts;

    public SaleGetOne(Sale sale) {
        this.id = sale.getId();
        this.sellerCode = sale.getSeller().getCode();
        this.sellerName = sale.getSeller().getName();
        this.clientId = sale.getClient().getId();
        this.clientName = sale.getClient().getName();
        this.total = sale.getTotal();
        this.discountTotal = sale.getDiscountTotal();
        this.saleStatus = sale.getSaleStatus().getName();
        this.saleProducts = sale.getSaleProducts();
    }

    public Integer getId() {
        return id;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public List<SaleProduct> getSaleProducts() {
        return saleProducts;
    }
}