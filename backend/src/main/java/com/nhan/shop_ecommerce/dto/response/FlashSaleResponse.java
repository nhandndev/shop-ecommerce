package com.nhan.shop_ecommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashSaleResponse {
    Long flashSaleId;
    String campaignName;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String state ;
    Boolean isPurchase;
    List<FlashSaleItemResponse> items;
}
