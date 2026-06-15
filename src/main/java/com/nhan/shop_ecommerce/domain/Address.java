package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "addresses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "contact_name",nullable = false)
    String contactName;

    @Column(name = "contact_phone",nullable = false)
    String contactPhone;

    @Column(name = "street_address",nullable = false)
    String streetAddress;

    @Column(name = "city",nullable = false)
    String city;

    @Column(name = "state_province",nullable = false)
    String stateProvince;

    @Column(name = "postal_code")
    String postalCode;

    @Column(name = "is_default",nullable = false, columnDefinition = "boolean default false")
    Boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user ;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders;


}
