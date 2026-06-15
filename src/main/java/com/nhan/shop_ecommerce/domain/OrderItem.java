package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    Long id;

    @Column(name = "quantity",nullable = false)
    int quantity;

    @Column(name = "price",nullable = false)
    double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    User seller;



}
