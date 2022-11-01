package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Seller;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.SellerRepository;
import com.alex.spring.vendas.requests.seller.SellerGet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Integer saveNewSeller(Seller newSeller) {
        return sellerRepository.save(newSeller).getId();
    }

    public Page<SellerGet> findSellers(Pageable pageable) {
        return sellerRepository.findAll(pageable).map(SellerGet::new);
    }

    public SellerGet findSellerById(Integer id) {
        return new SellerGet(sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Seller with id `%d`.".formatted(id))));
    }
}