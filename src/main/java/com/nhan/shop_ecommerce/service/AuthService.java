package com.nhan.shop_ecommerce.service;

import com.nhan.shop_ecommerce.dto.request.RegisterRequest;
import com.nhan.shop_ecommerce.dto.response.UserResponse;
import com.nhan.shop_ecommerce.enums.ErrorCode;
import com.nhan.shop_ecommerce.exception.AppException;
import com.nhan.shop_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    final UserRepository userRepository;
    public UserResponse register(RegisterRequest registerRequest){
        if(userRepository.existsByemail(registerRequest.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }
}
