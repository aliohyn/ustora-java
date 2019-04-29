package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.SlideItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideItemRepository extends CrudRepository<SlideItem, Long> {
    SlideItem findByName(String name);
    List<SlideItem> findAllByOrderByIdAsc();
}
