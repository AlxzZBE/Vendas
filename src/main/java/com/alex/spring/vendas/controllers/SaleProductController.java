package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.requests.saleproduct.SaleProductPost;
import com.alex.spring.vendas.services.SaleProductService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/saleProducts")
public class SaleProductController {

    private final SaleProductService saleProductService;

    public SaleProductController(SaleProductService saleProductService) {
        this.saleProductService = saleProductService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewSaleProduct(@RequestBody @Valid SaleProductPost form) {
        Integer saleProductSavedId = saleProductService.saveNewSaleProduct(form);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", saleProductSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }
}