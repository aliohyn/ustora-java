package com.aliohyn.ustora.repository;

import com.aliohyn.ustora.model.MenuItem;
import com.aliohyn.ustora.model.MenuType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByMenuTypeOrderBySortAsc(MenuType menuType);
    List<MenuItem> findAllByMenuTypeOrderByParentAsc(MenuType menuType);
}
