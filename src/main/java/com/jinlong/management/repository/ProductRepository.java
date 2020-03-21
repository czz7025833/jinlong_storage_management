package com.jinlong.management.repository;


import com.jinlong.management.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query("select distinct sku from Product")
    List<String> findDistinctProductBySku();

    Page<Product> findProductBySku(String sku, Pageable pageable);
}
