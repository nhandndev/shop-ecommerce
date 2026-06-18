package com.nhan.shop_ecommerce.strategy;

import com.nhan.shop_ecommerce.dto.response.HomeSectionResponse;
import com.nhan.shop_ecommerce.enums.HomeSectionKey;

public interface HomeSectionHandler {
    public HomeSectionResponse fetchDataSection(String title, int sortOrder);
    public HomeSectionKey getMyKey();
}
