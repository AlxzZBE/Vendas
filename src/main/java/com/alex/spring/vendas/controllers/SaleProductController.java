package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.domain.SaleProduct;
import com.alex.spring.vendas.requests.saleproduct.SaleProductGetList;
import com.alex.spring.vendas.requests.saleproduct.SaleProductGetOne;
import com.alex.spring.vendas.requests.saleproduct.SaleProductPost;
import com.alex.spring.vendas.services.SaleProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SaleProductGetList>> findSaleProducts(Pageable pageable) {
        return ResponseEntity.ok(saleProductService.findSaleProducts(pageable).map(SaleProductGetList::new));
    }

    @GetMapping(params = {"saleId", "productId"})
    public ResponseEntity<SaleProductGetOne> findSaleProductBySaleIdAndProductId(@RequestParam Integer saleId, @RequestParam Integer productId) {
        SaleProduct saleProductSaved = saleProductService.findSaleProductBySaleIdAndProductId(saleId, productId);
        return ResponseEntity.ok(new SaleProductGetOne(saleProductSaved));
    }

    @DeleteMapping(params = {"saleId", "productId"})
    public ResponseEntity<Void> deleteSaleProductBySaleIdAndProductId(@RequestParam Integer saleId, @RequestParam Integer productId) {
        saleProductService.deleteSaleProductBySaleIdAndProductId(saleId, productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(params = {"saleId", "productId", "amount"})
    public ResponseEntity<Void> updateSaleProductAmountById(@RequestParam Integer saleId,
                                                            @RequestParam Integer productId,
                                                            @RequestParam Integer amount) {
        saleProductService.updateSaleProductAmountById(saleId, productId, amount);
        return ResponseEntity.noContent().build();
    }
}