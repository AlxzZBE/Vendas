package com.alex.spring.vendas.requests.sale;

import com.alex.spring.vendas.domain.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleGetList {

    private Integer id;
    private String sellerCode;
    private String clientName;
    private BigDecimal total;
    private BigDecimal discountTotal;
    private String saleStatus;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    public SaleGetList(Sale sale) {
        this.id = sale.getId();
        this.sellerCode = sale.getSeller().getCode();
        this.clientName = sale.getClient().getName();
        this.total = sale.getTotal();
        this.discountTotal = sale.getDiscountTotal();
        this.saleStatus = sale.getSaleStatus().getName();
        this.createdAt = sale.getCreatedAt();
    }

    public Integer getId() {
        return id;
    }

    public String getSellerCode() {
        return sellerCode;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}