package com.nhan.shop_ecommerce.repository;

import com.nhan.shop_ecommerce.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findByActiveTrueOrderByUpdateAtAsc();
}
