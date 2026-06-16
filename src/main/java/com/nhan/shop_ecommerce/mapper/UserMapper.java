package com.nhan.shop_ecommerce.mapper;

import com.nhan.shop_ecommerce.domain.Role;
import com.nhan.shop_ecommerce.domain.User;
import com.nhan.shop_ecommerce.dto.request.RegisterRequest;
import com.nhan.shop_ecommerce.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  @Mapping(target = "roles", source = "roles", qualifiedByName = "roleToStringSet")
  UserResponse toUserResponse(User user);

  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "id", ignore = true)
  User toUser(RegisterRequest registerRequest);

  // Phương thức helper để chuyển đổi Set<Role> sang Set<String>
  @Named("roleToStringSet")
  default Set<String> roleToStringSet(Set<Role> roles) {
    if (roles == null) return null;
    return roles.stream()
            .map(Role::getName) // Giả sử class Role có phương thức getName()
            .collect(Collectors.toSet());
  }
}