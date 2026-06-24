package com.nhan.shop_ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "flash_sale_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flash_sale_id", nullable = false)
    FlashSale flashSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", nullable = false)
    ProductVariant productVariant;

    @Column(name = "flash_sale_price", nullable = false)
    Double flashSalePrice;

    @Column(name = "flash_sale_stock", nullable = false)
    int flashSaleStock;

    @Column(name = "flash_sale_sold", nullable = false)
    int flashSaleSold = 0;

    @Version
    @Column(name = "version", nullable = false)
    int version = 0;
}