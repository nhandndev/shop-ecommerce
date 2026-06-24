package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    Long id;

    @Column(name = "total_amount",nullable = false)
    Double totalAmount;

    @Column(name = "status",nullable = false)
    String status;

    @Column(name = "payment_method",nullable = false)
    String paymentMethod;

    @Column(name = "payment_status",nullable = false)
    String paymentStatus;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "version",nullable = false)
    int version;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    User buyer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems;

}
