package com.nhan.shop_ecommerce.dto.response;

import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeSectionResponse<T> {
    HomeSectionKey homeSectionKey;
    String title;
    int sortOrder;
    T data;

}
