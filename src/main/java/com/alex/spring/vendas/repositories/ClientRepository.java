package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Gender;
import com.alex.spring.vendas.domain.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Page<Client> findByNameIgnoreCase(String name, Pageable pageable);

    Page<Client> findByGender(Gender gender, Pageable pageable);

    Page<Client> findByLevel(Level level, Pageable pageable);
}