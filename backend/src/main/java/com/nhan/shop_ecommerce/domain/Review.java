package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.logging.Level;

@Entity
@Table(name = "reviews")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "rating",nullable = false)
    int rating;

    @Column(name = "comment")
    String comment;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    User buyer;

}
