package com.nhan.shop_ecommerce.service;

import com.nhan.shop_ecommerce.Utils.JwtUtils;
import com.nhan.shop_ecommerce.config.Entity.InvalidatedToken;
import com.nhan.shop_ecommerce.domain.Role;
import com.nhan.shop_ecommerce.domain.User;
import com.nhan.shop_ecommerce.dto.request.LoginRequest;
import com.nhan.shop_ecommerce.dto.request.LogoutRequest;
import com.nhan.shop_ecommerce.dto.request.RegisterRequest;
import com.nhan.shop_ecommerce.dto.response.AuthenticationResponse;
import com.nhan.shop_ecommerce.dto.response.UserResponse;
import com.nhan.shop_ecommerce.enums.ErrorCode;
import com.nhan.shop_ecommerce.exception.AppException;
import com.nhan.shop_ecommerce.mapper.UserMapper;
import com.nhan.shop_ecommerce.repository.InvalidatedTokenRepository;
import com.nhan.shop_ecommerce.repository.RoleRepository;
import com.nhan.shop_ecommerce.repository.UserRepository;
import com.nimbusds.jose.*;

import com.nimbusds.jwt.SignedJWT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PasswordEncoder passwordEncoder;
    final UserMapper userMapper;
    final JwtUtils jwtUtils;
    final InvalidatedTokenRepository invalidatedTokenRepository;
    @Transactional
    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByemail(registerRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if(userRepository.existsByphoneNumber(registerRequest.getPhoneNumber())){
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);
        }
        Role role = roleRepository.findByName("BUYER").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = userMapper.toUser(registerRequest);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
    public AuthenticationResponse login(LoginRequest loginRequest){
        User user = userRepository.findByemail(loginRequest.getEmail()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    public void logout(LogoutRequest logoutRequest){
        try{
            SignedJWT signedJWT = jwtUtils.verifyToken(logoutRequest.getToken());
            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(signedJWT.getJWTClaimsSet().getJWTID())
                    .expiredTime(signedJWT.getJWTClaimsSet().getExpirationTime())
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);
        }catch (ParseException | JOSEException exception){

        }
    }


}
