package com.nhan.shop_ecommerce.repository;

import com.nhan.shop_ecommerce.domain.HomeSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeSectionRepository extends JpaRepository<HomeSection,Long> {
    List<HomeSection> findByActiveTrueOrderBysortOrderAsc();
}
