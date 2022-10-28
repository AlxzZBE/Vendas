package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
