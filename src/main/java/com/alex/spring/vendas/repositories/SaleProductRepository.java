package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Integer> {
}
