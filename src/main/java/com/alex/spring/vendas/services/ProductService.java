package com.alex.spring.vendas.services;

import com.alex.spring.vendas.domain.Product;
import com.alex.spring.vendas.repositories.ProductRepository;
import com.alex.spring.vendas.requests.product.ProductGetList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Integer saveNewProduct(Product product, MultipartFile image) {
        Product savedProduct = productRepository.save(product);
        String imageName = "img_" + savedProduct.getId() + ".jpg";
        savedProduct.setImageName(imageName);
        return savedProduct.getId();
    }

    public Page<ProductGetList> findProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductGetList::new);
    }
}