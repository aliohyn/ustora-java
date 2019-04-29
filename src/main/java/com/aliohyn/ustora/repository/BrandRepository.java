package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findByName(String name);
    List<Brand> findAllByOrderByIdAsc();
}
