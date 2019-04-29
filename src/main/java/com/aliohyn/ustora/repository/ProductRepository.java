package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findByNewShow(Boolean newShow);
    List<Product> findByTopSellerShow(Boolean topSellerShow);
}
