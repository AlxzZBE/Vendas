package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.requests.sale.SaleGetList;
import com.alex.spring.vendas.requests.sale.SaleGetOne;
import com.alex.spring.vendas.requests.sale.SalePost;
import com.alex.spring.vendas.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewSale(@RequestBody @Valid SalePost form) {
        Long saleSavedId = saleService.saveNewSale(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", saleSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<SaleGetList>> findSales(Pageable pageable) {
        return ResponseEntity.ok(saleService.findSales(pageable).map(SaleGetList::new));
    }

    @GetMapping(params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleGetOne> findSaleById(@RequestParam Long id) {
        return ResponseEntity.ok(new SaleGetOne(saleService.findSaleById(id)));
    }

    @PatchMapping(params = {"id", "saleStatus"})
    public ResponseEntity<Void> updateSaleStatus(@RequestParam Long id, @RequestParam String saleStatus) {
        saleService.updateSaleStatus(id, saleStatus);
        return ResponseEntity.noContent().build();
    }
}