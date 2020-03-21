package com.jinlong.management.controllers.rest;


import com.jinlong.management.domain.Product;
import com.jinlong.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final int DEFAULT_PAGE_SIZE = 6;

    private final String ASC = "asc";

    @Autowired
    private ProductService productService;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "sku", required = false) String sku,
            @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE+"") int size, @RequestParam(value = "sort", defaultValue = "sku") String sortField
    , @RequestParam(value = "sortOrder", defaultValue = ASC) String sortOrder){
        Sort.Order order = new Sort.Order(sortOrder.equalsIgnoreCase(ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(page, size == 0? DEFAULT_PAGE_SIZE : size, Sort.by(order));
        Page<Product> products = productService.getProductList(sku, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("pagetotal", String.valueOf(products.getTotalElements()));
        headers.add("pagesize", String.valueOf(size));
        return new ResponseEntity<>(products.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable(value = "id") Long id){
        return productService.getProductById(id).orElseThrow(() -> new RuntimeException("product not exists"));
    }

    @GetMapping("/skus")
    @ResponseBody
    public List<String> getProductSkus(){
        return productService.getAllSkus();
    }

    @PostMapping()
    public void createProductByBatch(@RequestBody List<Product> products) {
        productService.createProductByBatch(products);
    }


    @PostMapping("/{id}")
    public void updateProductById(@RequestBody Product product) {
        productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteProductById(id);
    }

}
