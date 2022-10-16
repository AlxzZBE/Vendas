package com.alex.spring.vendas.repositories;

import com.alex.spring.vendas.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
