package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Product;
import com.alex.spring.vendas.domain.Sale;
import com.alex.spring.vendas.domain.SaleProduct;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.SaleProductRepository;
import com.alex.spring.vendas.requests.saleproduct.SaleProductPost;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<SaleProduct> findSaleProducts(Pageable pageable) {
        return saleProductRepository.findAll(pageable);
    }


    public SaleProduct findSaleProductBySaleIdAndProductId(Integer saleId, Integer productId) {
        return saleProductRepository.findBySaleIdAndProductId(saleId, productId)
                .orElseThrow(() -> new NotFoundException("" +
                        "Not Found SaleProduct with saleId `%d` and productId `%d`".formatted(saleId, productId)));
    }

    public void deleteSaleProductBySaleIdAndProductId(Integer saleId, Integer productId) {
        saleProductRepository.delete(findSaleProductBySaleIdAndProductId(saleId, productId));
    }
}