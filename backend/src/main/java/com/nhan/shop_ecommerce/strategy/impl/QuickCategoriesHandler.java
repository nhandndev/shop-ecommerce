package com.nhan.shop_ecommerce.strategy.impl;

import com.nhan.shop_ecommerce.domain.Category;
import com.nhan.shop_ecommerce.dto.response.CategoryResponse;
import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import com.nhan.shop_ecommerce.repository.CategoryRepository;
import com.nhan.shop_ecommerce.repository.HomeSectionRepository;
import com.nhan.shop_ecommerce.strategy.HomeSectionHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class QuickCategoriesHandler implements HomeSectionHandler {
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public HomeSectionResponse<List<CategoryResponse>> fetchDataSection(String title, int sortOrder) {
        List<CategoryResponse> categories = categoryRepository.findAll().stream().map(category ->
                        CategoryResponse.builder()
                                .id(category.getId())
                                .name(category.getName())
                                .imageUrl(category.getImageUrl())
                                .build()).toList();

        return HomeSectionResponse.<List<CategoryResponse>>builder()
                .homeSectionKey(getMyKey())
                .title(title)
                .sortOrder(sortOrder)
                .data(categories)
                .build();
    }
    @Override
    public HomeSectionKey getMyKey() {
        return HomeSectionKey.QUICK_CATEGORIES;
    }
}
