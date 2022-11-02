package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
