package com.nhan.shop_ecommerce.strategy.impl;

import com.nhan.shop_ecommerce.domain.ProductVariant;
import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.dto.response.ProductSummaryResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.repository.ProductRepository;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecommendGrid implements HomeSectionHandler {
    final ProductRepository productRepository;
    @Override
    @Transactional
    public HomeSectionResponse<List<ProductSummaryResponse>> fetchDataSection(String title, int sortOrder){
        List<ProductSummaryResponse> products= productRepository.findByActiveTrueOrderByUpdatedAtAsc().stream()
                .filter(product -> !product.getProductVariants().isEmpty())
                .map(product -> {
                    Double maxPrice = product.getProductVariants().stream().map(ProductVariant::getPrice).max(Double::compare).orElse(null);
                    Double minPrice = product.getProductVariants().stream().map(ProductVariant::getPrice).min(Double::compare).orElse(null);
                    return ProductSummaryResponse.builder()
                            .id(product.getId())
                            .title(product.getTitle())
                            .mainImage(product.getMainImage())
                            .minPrice(minPrice)
                            .maxPrice(maxPrice)
                            .build();
                        })
                .toList();
        return HomeSectionResponse.<List<ProductSummaryResponse>>builder()
                .homeSectionKey(getMyKey())
                .title(title)
                .sortOrder(sortOrder)
                .data(products)
                .build();


    }

    @Override
    public HomeSectionKey getMyKey() {
        return HomeSectionKey.RECOMMENDED_GRID;
    }
}
