package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Product;
import com.alex.spring.vendas.domain.Sale;
import com.alex.spring.vendas.domain.SaleProduct;
import com.alex.spring.vendas.repositories.SaleProductRepository;
import com.alex.spring.vendas.requests.saleproduct.SaleProductPost;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaleProductService {

    private final SaleProductRepository saleProductRepository;
    private final ProductService productService;
    private final SaleService saleService;

    public SaleProductService(SaleProductRepository saleProductRepository, ProductService productService, SaleService saleService) {
        this.saleProductRepository = saleProductRepository;
        this.productService = productService;
        this.saleService = saleService;
    }

    @Transactional
    public Integer saveNewSaleProduct(SaleProductPost saleProductPost) {
        Product productSaved = productService.findProductById(saleProductPost.getProductId());
        Sale saleSaved = saleService.findSaleById(saleProductPost.getSaleId());
        BigDecimal totalPrice = productSaved.getPrice().multiply(BigDecimal.valueOf(saleProductPost.getAmount()));

        SaleProduct newSaleProduct = new SaleProduct();
        newSaleProduct.setProduct(productSaved);
        newSaleProduct.setSale(saleSaved);
        newSaleProduct.setAmount(saleProductPost.getAmount());
        newSaleProduct.setUnitPrice(productSaved.getPrice());
        newSaleProduct.setTotalPrice(totalPrice.subtract(saleProductPost.getDiscount()));
        newSaleProduct.setDiscountPrice(saleProductPost.getDiscount());

        saleSaved.setTotalPrice(saleSaved.getTotalPrice().add(newSaleProduct.getTotalPrice()));
        return saleProductRepository.save(newSaleProduct).getSale().getId();
    }
}