package com.nhan.shop_ecommerce.repository;

import com.nhan.shop_ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByfullName(String userName);
    boolean existsByemail(String userName);

}
