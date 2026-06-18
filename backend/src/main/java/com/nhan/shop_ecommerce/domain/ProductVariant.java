package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product_variants")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    Long id;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "price",nullable = false)
    Double price;

    @Column(name = "stock",nullable = false)
    int stock;

    @Version
    @Column(name = "version",nullable = false)
    int version = 0 ;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems;
}
