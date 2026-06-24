package com.nhan.shop_ecommerce.domain;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate // dien thoi gian khi tao
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy // tu dong dien username nguoi tao tu spring security context khi insert
    @Column(name = "created_by", updatable = false,nullable = false)
    private String createdBy;

    @LastModifiedDate // tu dong cap nhat thoi gian update
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy //tu dong dien username nguoi update
    @Column(name = "updated_by")
    private String updatedBy;
}