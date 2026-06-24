package com.nhan.shop_ecommerce.strategy.impl;


import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlashSaleHandler extends HomeSectionHandler {
    @Override
    public HomeSectionResponse fetchDataSection(String title, int sortOrder){

    }
    @Override
    public HomeSectionKey getMyKey(){
        return HomeSectionKey.FLASH_SALE;
    }
}
