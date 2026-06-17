package com.nhan.shop_ecommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserResponse {
    Long id;
    String email;
    String fullName;
    String phoneNumber;
    Set<String> roles;
}
