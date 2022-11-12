package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Client;
import com.alex.spring.vendas.domain.Sale;
import com.alex.spring.vendas.domain.SaleStatus;
import com.alex.spring.vendas.domain.Seller;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.SaleRepository;
import com.alex.spring.vendas.requests.sale.SalePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaleService {

    private final SellerService sellerService;
    private final ClientService clientService;
    private final SaleRepository saleRepository;

    public SaleService(SellerService sellerService, ClientService clientService, SaleRepository saleRepository) {
        this.sellerService = sellerService;
        this.clientService = clientService;
        this.saleRepository = saleRepository;
    }

    public Long saveNewSale(SalePost salePost) {
        Seller sellerSaved = sellerService.findSellerByCode(salePost.getSellerCode());
        Client clientSaved = clientService.findClientById(salePost.getClientId());

        Sale newSale = new Sale();
        newSale.setSeller(sellerSaved);
        newSale.setClient(clientSaved);
        newSale.setCreatedAt(LocalDateTime.now());
        return saleRepository.save(newSale).getId();
    }

    public Page<Sale> findSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    public Sale findSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Sale With id `%d`.".formatted(id)));
    }

    public void updateSaleStatus(Long id, String saleStatus) {
        Sale saleSaved = findSaleById(id);
        SaleStatus newSaleStatus = SaleStatus.newSaleStatus(saleStatus);

        if (newSaleStatus == SaleStatus.DONE) {
            Seller saleSavedSeller = saleSaved.getSeller();
            saleSavedSeller.setTotalSales(saleSavedSeller.getTotalSales() + 1);
        }
        saleSaved.setSaleStatus(newSaleStatus);
        saleRepository.save(saleSaved);
    }
}