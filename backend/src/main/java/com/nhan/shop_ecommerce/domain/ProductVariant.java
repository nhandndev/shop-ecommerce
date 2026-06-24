package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_variants")
public class ProductVariant extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "stock", nullable = false)
    int stock;

    @Column(name = "active",nullable = false)
    boolean active ;

    @Version
    @Column(name = "version", nullable = false)
    int version = 0;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems;

    @OneToMany(mappedBy = "productVariant",cascade = CascadeType.ALL,orphanRemoval = true)
    List<FlashSaleItem> flashSaleItems;

}

