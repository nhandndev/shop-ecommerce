package com.nhan.shop_ecommerce.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleItemResponse {
    // lay tu product
    Long productId;
    String productTitle;
    String image;

    //lay tu variant
    Long variantId;
    String variantName;
    Double originPrice;

    //lay thong tin flashSale
    Double flashSalePrice;
    int flashSaleStock;
    int flashSaleSold;
}


