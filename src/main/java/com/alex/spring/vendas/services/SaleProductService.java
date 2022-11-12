package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Product;
import com.alex.spring.vendas.domain.Sale;
import com.alex.spring.vendas.domain.SaleProduct;
import com.alex.spring.vendas.domain.SaleStatus;
import com.alex.spring.vendas.exceptions.BusinessLogicException;
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
    public Long saveNewSaleProduct(SaleProductPost saleProductPost) {
        if (saleProductRepository.existsBySaleIdAndProductId(saleProductPost.getSaleId(), saleProductPost.getProductId())) {
            SaleProduct saleProductSaved = saleProductRepository.findBySaleIdAndProductId(saleProductPost.getSaleId(), saleProductPost.getProductId()).get();

            if (saleProductPost.getAmount() <= 0) {
                deleteSaleProductBySaleIdAndProductId(saleProductPost.getSaleId(), saleProductPost.getProductId());
                return 0L;
            }

            BigDecimal oldSaleTotalPrice = saleProductSaved.getSale().getTotalPrice();
            BigDecimal oldSaleProductTotalPrice = saleProductSaved.getTotalPrice();
            saleProductSaved.getSale().setTotalPrice(oldSaleTotalPrice.subtract(oldSaleProductTotalPrice));

            saleProductSaved.setAmount(saleProductPost.getAmount());

            BigDecimal newSaleProductTotalPrice = saleProductSaved.getUnitPrice().multiply(BigDecimal.valueOf(saleProductSaved.getAmount()));
            saleProductSaved.setTotalPrice(newSaleProductTotalPrice);

            BigDecimal newSaleTotalPrice = saleProductSaved.getSale().getTotalPrice();
            saleProductSaved.getSale().setTotalPrice(newSaleTotalPrice.add(newSaleProductTotalPrice));

            return saleProductRepository.save(saleProductSaved).getSale().getId();
        }

        Product productSaved = productService.findProductById(saleProductPost.getProductId());
        Sale saleSaved = saleService.findSaleById(saleProductPost.getSaleId());

        SaleProduct newSaleProduct = new SaleProduct();
        newSaleProduct.setProduct(productSaved);
        newSaleProduct.setSale(saleSaved);
        newSaleProduct.setAmount(saleProductPost.getAmount() >= 1 ? saleProductPost.getAmount() : 1);
        newSaleProduct.setUnitPrice(productSaved.getPrice());
        newSaleProduct.setDiscountPrice(saleProductPost.getDiscount());

        BigDecimal newSaleProductTotalPrice = productSaved.getPrice().multiply(BigDecimal.valueOf(newSaleProduct.getAmount()));
        newSaleProduct.setTotalPrice(newSaleProductTotalPrice);

        saleSaved.setTotalPrice(saleSaved.getTotalPrice().add(newSaleProduct.getTotalPrice()));
        return saleProductRepository.save(newSaleProduct).getSale().getId();
    }

    public Page<SaleProduct> findSaleProducts(Pageable pageable) {
        return saleProductRepository.findAll(pageable);
    }


    public SaleProduct findSaleProductBySaleIdAndProductId(Long saleId, Long productId) {
        return saleProductRepository.findBySaleIdAndProductId(saleId, productId)
                .orElseThrow(() -> new NotFoundException("" +
                        "Not Found SaleProduct with saleId `%d` and productId `%d`".formatted(saleId, productId)));
    }

    public void deleteSaleProductBySaleIdAndProductId(Long saleId, Long productId) {
        SaleProduct saleProductSaved = findSaleProductBySaleIdAndProductId(saleId, productId);
        checkSaleStatusAndThrowException(saleProductSaved);

        BigDecimal subtract = saleProductSaved.getSale().getTotalPrice().subtract(saleProductSaved.getTotalPrice());
        saleProductSaved.getSale().setTotalPrice(subtract);
        saleProductRepository.delete(saleProductSaved);
    }

    public void updateSaleProductAmountById(Long saleId, Long productId, Integer amount) {
        if (amount <= 0) {
            deleteSaleProductBySaleIdAndProductId(saleId, productId);
            return;
        }

        SaleProduct saleProductSaved = findSaleProductBySaleIdAndProductId(saleId, productId);
        checkSaleStatusAndThrowException(saleProductSaved);

        BigDecimal oldProductTotalPrice = saleProductSaved.getTotalPrice();
        BigDecimal oldSaleTotalPrice = saleProductSaved.getSale().getTotalPrice();
        saleProductSaved.getSale().setTotalPrice(oldSaleTotalPrice.subtract(oldProductTotalPrice));

        saleProductSaved.setAmount(amount);

        BigDecimal newProductTotalPrice = saleProductSaved.getUnitPrice().multiply(BigDecimal.valueOf(amount));
        BigDecimal newSaleTotalPrice = saleProductSaved.getSale().getTotalPrice();
        saleProductSaved.setTotalPrice(newProductTotalPrice);
        saleProductSaved.getSale().setTotalPrice(newSaleTotalPrice.add(newProductTotalPrice));

        saleProductRepository.save(saleProductSaved);
    }

    public void updateSaleProductUnitPriceById(Long saleId, Long productId, BigDecimal unitPrice) {
        SaleProduct saleProductSaved = findSaleProductBySaleIdAndProductId(saleId, productId);
        checkSaleStatusAndThrowException(saleProductSaved);

        BigDecimal oldProductTotalPrice = saleProductSaved.getTotalPrice();
        BigDecimal oldSaleTotalPrice = saleProductSaved.getSale().getTotalPrice();
        saleProductSaved.getSale().setTotalPrice(oldSaleTotalPrice.subtract(oldProductTotalPrice));

        saleProductSaved.setUnitPrice(unitPrice);

        BigDecimal newProductTotalPrice = saleProductSaved.getUnitPrice().multiply(BigDecimal.valueOf(saleProductSaved.getAmount()));
        BigDecimal newSaleTotalPrice = saleProductSaved.getSale().getTotalPrice();
        saleProductSaved.setTotalPrice(newSaleTotalPrice);
        saleProductSaved.getSale().setTotalPrice(newSaleTotalPrice.add(newProductTotalPrice));

        saleProductRepository.save(saleProductSaved);
    }

    private void checkSaleStatusAndThrowException(SaleProduct saleProduct) {
        SaleStatus saleProductSavedSaleStatus = saleProduct.getSale().getSaleStatus();
        if (saleProductSavedSaleStatus.equals(SaleStatus.DONE) || saleProductSavedSaleStatus.equals(SaleStatus.CANCELLED)) {
            throw new BusinessLogicException(
                    "Cannot remove saleProduct that has a sale with saleStatus equals `Concluída` or `Cancelada`.");
        }
    }
}