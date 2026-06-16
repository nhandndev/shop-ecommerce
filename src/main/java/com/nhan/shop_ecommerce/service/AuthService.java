package com.nhan.shop_ecommerce.service;

import com.nhan.shop_ecommerce.config.PasswordEnconder;
import com.nhan.shop_ecommerce.domain.Role;
import com.nhan.shop_ecommerce.domain.User;
import com.nhan.shop_ecommerce.dto.request.RegisterRequest;
import com.nhan.shop_ecommerce.dto.response.UserResponse;
import com.nhan.shop_ecommerce.enums.ErrorCode;
import com.nhan.shop_ecommerce.exception.AppException;
import com.nhan.shop_ecommerce.mapper.UserMapper;
import com.nhan.shop_ecommerce.repository.RoleRepository;
import com.nhan.shop_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEnconder passwordEnconder;
    final UserMapper userMapper;
    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByemail(registerRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Role role = roleRepository.findByName("BUYER").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = userMapper.toUser(registerRequest);
        user.setRoles(roles);
        user.setPassword(passwordEnconder.PasswordEncoder().encode(registerRequest.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}
