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
        SaleProduct saleProductSaved = findSaleProductBySaleIdAndProductId(saleId, productId);
        checkSaleStatusAndThrowException(saleProductSaved);
        saleProductRepository.delete(saleProductSaved);
    }

    public void updateSaleProductAmountById(Integer saleId, Integer productId, Integer amount) {
        if (amount <= 0) {
            deleteSaleProductBySaleIdAndProductId(saleId, productId);
            return;
        }

        SaleProduct saleProductSaved = findSaleProductBySaleIdAndProductId(saleId, productId);
        checkSaleStatusAndThrowException(saleProductSaved);

        saleProductSaved.setAmount(amount);
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