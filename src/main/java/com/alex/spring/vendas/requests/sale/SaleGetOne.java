package com.alex.spring.vendas.requests.sale;

import com.alex.spring.vendas.domain.Sale;
import com.alex.spring.vendas.requests.saleproduct.SaleProductGetOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleGetOne {

    private Integer id;
    private String sellerName;
    private String sellerCode;
    private String clientName;
    private Integer clientId;
    private String saleStatus;
    private BigDecimal discountTotal;
    private BigDecimal totalPrice;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;
    private List<SaleProductGetOne> saleProducts;

    public SaleGetOne(Sale sale) {
        this.id = sale.getId();
        this.sellerCode = sale.getSeller().getCode();
        this.sellerName = sale.getSeller().getName();
        this.clientId = sale.getClient().getId();
        this.clientName = sale.getClient().getName();
        this.totalPrice = sale.getTotalPrice();
        this.discountTotal = sale.getDiscountTotal();
        this.saleStatus = sale.getSaleStatus().getName();
        this.createdAt = sale.getCreatedAt();
        this.saleProducts = sale.getSaleProducts().stream().map(SaleProductGetOne::new).toList();
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<SaleProductGetOne> getSaleProducts() {
        return saleProducts;
    }
}