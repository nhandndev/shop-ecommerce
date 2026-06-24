package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flash_sale")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class FlashSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "campaign_name",nullable = false ,length = 50)
    String campaignName;

    @Column(name = "start_time",nullable = false )
    LocalDateTime startTime;

    @Column(name = "end_time",nullable = false )
    LocalDateTime endTime;

    @Column(name = "status",nullable = false )
    Boolean status;

    @OneToMany(mappedBy = "flashSale", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FlashSaleItem> flashSaleItems;


}
