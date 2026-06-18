package com.nhan.shop_ecommerce.repository;

import com.nhan.shop_ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
