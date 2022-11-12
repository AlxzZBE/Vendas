package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Page<Seller> findByNameIgnoreCase(String name, Pageable pageable);

    Optional<Seller> findByCodeIgnoreCase(String code);

    boolean existsByCode(String code);
}