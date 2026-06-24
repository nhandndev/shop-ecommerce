package com.nhan.shop_ecommerce.repository;

import com.nhan.shop_ecommerce.domain.FlashSale;
import com.nhan.shop_ecommerce.domain.FlashSaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale,Long> {
    List<FlashSale> findByStatusTrueOrderByStartTimeAsc();
}
