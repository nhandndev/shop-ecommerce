package com.nhan.shop_ecommerce.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    Long id;
    String name;
    String imageUrl;

}
