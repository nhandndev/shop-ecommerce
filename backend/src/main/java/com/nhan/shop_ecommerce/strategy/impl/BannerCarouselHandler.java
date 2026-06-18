package com.nhan.shop_ecommerce.strategy.impl;

import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class BannerCarouselHandler implements HomeSectionHandler {
    @Override
    public HomeSectionResponse fetchDataSection(String title, int sortOrder){
        List<Map<String, String>> banners = List.of(
                Map.of("image", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e", "url", "/events/electronic-sale"),
                Map.of("image", "https://images.unsplash.com/photo-1544441893-675973e31985", "url", "/collections/fashion-winter")
        );

        return HomeSectionResponse.builder()
                .homeSectionKey(getMyKey())
                .title(title)
                .sortOrder(sortOrder)
                .data(banners)
                .build();
    }
    @Override
    public HomeSectionKey getMyKey(){
        return HomeSectionKey.BANNER_CAROUSEL;
    }
}
