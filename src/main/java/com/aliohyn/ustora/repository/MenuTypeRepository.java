package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.MenuType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuTypeRepository extends CrudRepository<MenuType, Long> {
    MenuType findByName(String name);
}
