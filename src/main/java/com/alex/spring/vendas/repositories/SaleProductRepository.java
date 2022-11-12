package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {

    Optional<SaleProduct> findBySaleIdAndProductId(Long saleId, Long productId);

    boolean existsBySaleIdAndProductId(Long saleId, Long productId);
}
