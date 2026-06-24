package com.nhan.shop_ecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class FlashSaleResponse {
    private Long flashSaleId;
    private String campaignName;
    private LocalDateTime endTime;
    private List<FlashSaleItemResponse> items;
}
