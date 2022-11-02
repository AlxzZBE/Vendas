package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.requests.seller.SellerGet;
import com.alex.spring.vendas.requests.seller.SellerPost;
import com.alex.spring.vendas.services.SellerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveNewSeller(@RequestBody @Valid SellerPost form) {
        Integer sellerSavedId = sellerService.saveNewSeller(form.newSeller());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", sellerSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<SellerGet>> findSellers(Pageable pageable) {
        return ResponseEntity.ok(sellerService.findSellers(pageable).map(SellerGet::new));
    }

    @GetMapping(params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SellerGet> findSellerById(@RequestParam Integer id) {
        return ResponseEntity.ok(new SellerGet(sellerService.findSellerById(id)));
    }

    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SellerGet>> findSellerByName(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(sellerService.findSellerByName(name, pageable).map(SellerGet::new));
    }
}