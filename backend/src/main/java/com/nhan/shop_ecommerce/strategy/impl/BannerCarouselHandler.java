package com.nhan.shop_ecommerce.strategy.impl;

import com.nhan.shop_ecommerce.domain.Banner;
import com.nhan.shop_ecommerce.dto.response.BannerResponse;
import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.repository.BannerRepository;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BannerCarouselHandler implements HomeSectionHandler {
    final BannerRepository bannerRepository ;
    @Override
    @Transactional
    public HomeSectionResponse<List<BannerResponse>> fetchDataSection(String title, int sortOrder){
        List<BannerResponse> banners = bannerRepository.findByActiveTrueOrderByDisplayOrderAsc().stream().map(banner ->
                BannerResponse.builder()
                        .imageUrl(banner.getImageUrl())
                        .targetUrl(banner.getTargetUrl())
                        .build()).toList();

        return HomeSectionResponse.<List<BannerResponse>>builder()
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
