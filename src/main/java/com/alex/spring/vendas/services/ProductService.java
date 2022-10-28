package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Product;
import com.alex.spring.vendas.exceptions.ArgumentNotValidException;
import com.alex.spring.vendas.exceptions.NotFoundException;
import com.alex.spring.vendas.repositories.ProductRepository;
import com.alex.spring.vendas.requests.product.ProductGetList;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
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

    public void updateProductPriceById(Integer id, BigDecimal price) {
        Product productSaved = findProductById(id);
        productSaved.setPrice(price);
        productRepository.save(productSaved);
    }

    public void updateProductNameById(Integer id, String name) {
        if (name.length() < 3) {
            throw new ArgumentNotValidException("The field `name` should've minimum three characters.");
        }
        Product productSaved = findProductById(id);
        productSaved.setName(name);
        productRepository.save(productSaved);
    }

    public void deleteProductById(Integer id) {
        findProductById(id);
        productRepository.deleteById(id);
    }
}