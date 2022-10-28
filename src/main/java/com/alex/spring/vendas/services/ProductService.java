package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Product;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.ProductRepository;
import com.alex.spring.vendas.requests.product.ProductGetList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Integer saveNewProduct(Product product, MultipartFile image) {
        Product savedProduct = productRepository.save(product);
        String imageName = "img_" + savedProduct.getId() + ".jpg";
        savedProduct.setImageName(imageName);
        productRepository.save(savedProduct);
        return savedProduct.getId();
    }

    public Page<ProductGetList> findProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductGetList::new);
    }

    public Product findProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Product with id `%d`.".formatted(id)));
    }

    public Product findProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Not Found Product with name `%s`.".formatted(name)));
    }

    public void updateProductAmountById(Integer id, Long amount) {
        Product productSaved = findProductById(id);
        productSaved.setAmount(amount);
        productRepository.save(productSaved);
    }
}