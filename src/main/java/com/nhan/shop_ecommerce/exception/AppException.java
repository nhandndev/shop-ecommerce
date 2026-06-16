package com.nhan.shop_ecommerce.exception;

import com.nhan.shop_ecommerce.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppException extends RuntimeException{
    private final ErrorCode errorCode;
}
