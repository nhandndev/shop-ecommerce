package com.nhan.shop_ecommerce.config.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class InvalidatedToken {
    @Id
    String id;
    Date expiredTime;
}
