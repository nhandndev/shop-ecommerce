package com.nhan.shop_ecommerce.repository;

import com.nhan.shop_ecommerce.config.Entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken,String> {
    boolean existsById(String id);

}
