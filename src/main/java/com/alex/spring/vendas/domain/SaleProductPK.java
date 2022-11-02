package com.alex.spring.vendas.domain;

public class SaleProductPK {

    private Product product;
    private Sale sale;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
