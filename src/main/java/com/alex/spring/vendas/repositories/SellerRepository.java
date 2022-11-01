package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

}