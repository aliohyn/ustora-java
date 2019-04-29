package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends CrudRepository<Section, Long> {
    Section findByName(String name);
    List<Section> findAllByOrderByIdAsc();
}