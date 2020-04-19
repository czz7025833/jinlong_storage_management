package com.jinlong.management.service;


import com.jinlong.management.domain.Product;
import com.jinlong.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public List<String> getAllSkus(){
        return productRepository.findDistinctProductBySku();
    }

    public Page<Product> getProductList(String sku, Pageable pageable){
        if(sku != null && sku != ""){
            return productRepository.findProductBySku(sku, pageable);
        }
        return productRepository.findAll(pageable);
    }

    public Product createOrUpdateProduct(Product product){
        return productRepository.save(product);
    }

    public void createProductByBatch(List<Product> products){
        products.forEach(product -> productRepository.save(product));
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
