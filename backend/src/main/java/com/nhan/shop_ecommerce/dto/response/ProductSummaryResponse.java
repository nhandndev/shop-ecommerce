package com.nhan.shop_ecommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSummaryResponse {
    Long id;
    String title;
    String mainImage;
    Double minPrice;
    Double maxPrice;
}
