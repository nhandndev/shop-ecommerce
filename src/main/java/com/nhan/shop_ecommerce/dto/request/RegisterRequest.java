package com.nhan.shop_ecommerce.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class RegisterRequest {
    @Email(message = "EMAIL_INVALID")
    @NotBlank(message = "EMAIL_REQUIRED")
    private String email;

    @Size(min = 6, message = "PASSWORD_INVALID")
    @NotBlank(message = "PASSWORD_REQUIRED")
    private String password;

    @NotBlank(message = "FULLNAME_REQUIRED")
    private String fullName;

    private String phoneNumber;
}
