package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title",nullable = false)
    String title;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "main_image")
    String mainImage;

    @Column(name = "active")
    boolean active;

    @ManyToOne
    @JoinColumn(name="category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name="seller_id")
    User seller;



    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    Set<ProductVariant> productVariants ;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , orphanRemoval = true)
    List<Review> reviews;








}
