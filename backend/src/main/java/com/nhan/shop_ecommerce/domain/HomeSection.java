package com.nhan.shop_ecommerce.domain;

import com.nhan.shop_ecommerce.enums.HomeSectionKey;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "home_sections")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_key", nullable = false, unique = true)
    HomeSectionKey homeSectionKey;

    @Column(name = "title",nullable = false ,length = 50)
    String title;

    @Column(name = "sortOrder",nullable = false)
    int sortOrder;

    @Column(name = "active",nullable = false)
    boolean active;

}
