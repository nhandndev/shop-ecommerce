package com.nhan.shop_ecommerce.mapper;

import com.nhan.shop_ecommerce.domain.User;
import com.nhan.shop_ecommerce.dto.request.RegisterRequest;
import com.nhan.shop_ecommerce.dto.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-16T17:10:59+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.roles( roleToStringSet( user.getRoles() ) );
        userResponse.id( user.getId() );
        userResponse.email( user.getEmail() );
        userResponse.fullName( user.getFullName() );
        userResponse.phoneNumber( user.getPhoneNumber() );

        return userResponse.build();
    }

    @Override
    public User toUser(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( registerRequest.getEmail() );
        user.fullName( registerRequest.getFullName() );
        user.phoneNumber( registerRequest.getPhoneNumber() );

        return user.build();
    }
}
