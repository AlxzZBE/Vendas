package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.requests.product.ProductGetList;
import com.alex.spring.vendas.requests.product.ProductGetOne;
import com.alex.spring.vendas.requests.product.ProductPost;
import com.alex.spring.vendas.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveNewProduct(@RequestPart("form") @Valid ProductPost form, @RequestPart("image") MultipartFile image) {
        Integer productSavedId = productService.saveNewProduct(form.newProduct(), image);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", productSavedId).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductGetList>> findProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.findProducts(pageable).map(ProductGetList::new));
    }

    @GetMapping(params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductGetOne> findProductById(@RequestParam Integer id) {
        return ResponseEntity.ok(new ProductGetOne(productService.findProductById(id)));
    }

    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductGetOne> findProductByName(@RequestParam String name) {
        return ResponseEntity.ok(new ProductGetOne(productService.findProductByName(name)));
    }

    @PatchMapping(path = "{id}", params = "amount")
    public ResponseEntity<Void> updateProductAmountById(@PathVariable Integer id, @RequestParam Long amount) {
        productService.updateProductAmountById(id, amount);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{id}", params = "price")
    public ResponseEntity<Void> updateProductPriceById(@PathVariable Integer id, @RequestParam BigDecimal price) {
        productService.updateProductPriceById(id, price);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "{id}", params = "name")
    public ResponseEntity<Void> updateProductNameById(@PathVariable Integer id, @RequestParam String name) {
        productService.updateProductNameById(id, name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteProductbyId(@RequestParam Integer id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}