package com.nhan.shop_ecommerce.controller;

import com.nhan.shop_ecommerce.dto.request.RegisterRequest;
import com.nhan.shop_ecommerce.dto.response.ApiResponse;
import com.nhan.shop_ecommerce.dto.response.UserResponse;
import com.nhan.shop_ecommerce.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {
    final AuthService authService;
    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody RegisterRequest registerRequest){
        return  ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("register completed")
                .data(authService.register(registerRequest))
                .build();
    }
}
