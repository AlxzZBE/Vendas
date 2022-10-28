package com.alex.spring.vendas.controllers;

import com.alex.spring.vendas.requests.client.ClientGetList;
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
        return ResponseEntity.ok(productService.findProducts(pageable));
    }

    @GetMapping(params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductGetOne> findProductById(@RequestParam Integer id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }
}