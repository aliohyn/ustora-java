package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getItemsByPage(int page, int size);
    Long getCount();
}
