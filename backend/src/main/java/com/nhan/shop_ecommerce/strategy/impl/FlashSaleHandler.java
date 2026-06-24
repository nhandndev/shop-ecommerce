package com.nhan.shop_ecommerce.strategy.impl;


import com.nhan.shop_ecommerce.domain.FlashSale;
import com.nhan.shop_ecommerce.dto.response.FlashSaleItemResponse;
import com.nhan.shop_ecommerce.dto.response.FlashSaleResponse;
import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.repository.FlashSaleRepository;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
public class FlashSaleHandler implements HomeSectionHandler {
    final FlashSaleRepository flashSaleRepository;

    @Override
    public HomeSectionResponse< List<FlashSaleResponse>> fetchDataSection(String title, int sortOrder){
        List<FlashSale> campaigns = flashSaleRepository.findByStatusTrueOrderByStartTimeAsc();
        LocalDateTime time = LocalDateTime.now();
        if(campaigns == null || campaigns.isEmpty()){
            return null;
        }

        List<FlashSaleResponse> data = campaigns.stream()
                .filter(campaign -> campaign.getEndTime().isAfter(time.minusHours(1)))
                .map(campaign ->{
                    String state = time.isBefore(campaign.getStartTime()) ? "CAMPAIGN COMMING UP" : (time.isAfter(campaign.getEndTime())?"CAMPAIGN END":"CAMPAIGN IS STARTING");
                    return FlashSaleResponse.builder()
                            .flashSaleId(campaign.getId())
                            .startTime(campaign.getStartTime())
                            .endTime(campaign.getEndTime())
                            .state(state)
                            .isPurchase("CAMPAIGN IS STARTING".equals(state))
                            .items(campaign.getFlashSaleItems().stream()
                                    .map(flashSaleItem -> FlashSaleItemResponse.builder()
                                                    .productId(flashSaleItem.getProductVariant().getProduct().getId())
                                                    .productTitle(flashSaleItem.getProductVariant().getProduct().getTitle())
                                                    .image(flashSaleItem.getProductVariant().getProduct().getMainImage())
                                                    .variantId(flashSaleItem.getProductVariant().getId())
                                                    .variantName(flashSaleItem.getProductVariant().getName())
                                                    .originPrice(flashSaleItem.getProductVariant().getPrice())
                                                    .flashSalePrice(flashSaleItem.getFlashSalePrice())
                                                    .flashSaleStock(flashSaleItem.getFlashSaleStock())
                                                    .flashSaleSold(flashSaleItem.getFlashSaleSold())
                                                    .build()
                                            )
                                    .toList())
                            .build();
                }
                )
                .toList();
        return HomeSectionResponse.<List<FlashSaleResponse>>builder()
                .homeSectionKey(getMyKey())
                .title(title)
                .sortOrder(sortOrder)
                .data(data)
                .build();
    }


    @Override
    public HomeSectionKey getMyKey(){
        return HomeSectionKey.FLASH_SALE;
    }
}
