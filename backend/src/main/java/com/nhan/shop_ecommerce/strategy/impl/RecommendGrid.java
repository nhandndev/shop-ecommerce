package com.nhan.shop_ecommerce.strategy.impl;

import com.nhan.shop_ecommerce.domain.Product;
import com.nhan.shop_ecommerce.domain.ProductVariant;
import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.dto.response.ProductSummaryResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.repository.ProductRepository;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class RecommendGrid implements HomeSectionHandler {
    final ProductRepository productRepository;
    @Override
    public HomeSectionResponse<List<ProductSummaryResponse>> fetchDataSection(String title, int sortOrder){
        List<ProductSummaryResponse> products= productRepository.findByActiveTrueOrderByUpdateAtAsc().stream()
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
                .sortOrder(sortOrder)C
                .data(products)
                .build();


    }

    @Override
    public HomeSectionKey getMyKey() {
        return HomeSectionKey.RECOMMENDED_GRID;
    }
}
