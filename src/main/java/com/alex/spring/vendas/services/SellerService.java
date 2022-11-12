package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Seller;
import com.alex.spring.vendas.exceptions.AlreadyExistsException;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.SellerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Long saveNewSeller(Seller seller) {
        if (sellerRepository.existsByCode(seller.getCode())) {
            throw new AlreadyExistsException("Already exists a product with name `%s`.".formatted(seller.getName()));
        }
        return sellerRepository.save(seller).getId();
    }

    public Page<Seller> findSellers(Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }

    public Seller findSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Seller with id `%d`.".formatted(id)));
    }

    public Page<Seller> findSellerByName(String name, Pageable pageable) {
        return sellerRepository.findByNameIgnoreCase(name, pageable);
    }

    public Seller findSellerByCode(String code) {
        return sellerRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new NotFoundException("Not Found Seller With Code `%s`.".formatted(code)));
    }
}