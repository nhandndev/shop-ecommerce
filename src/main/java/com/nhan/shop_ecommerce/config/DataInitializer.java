package com.nhan.shop_ecommerce.config;

import com.nhan.shop_ecommerce.domain.Permission;
import com.nhan.shop_ecommerce.domain.Role;
import com.nhan.shop_ecommerce.repository.PermissionRepository;
import com.nhan.shop_ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Bean
    public ApplicationRunner initializeData() {
        return args -> {
            log.info("==> Khởi chạy tiến trình đồng bộ dữ liệu Roles và Permissions...");

            // 1. KHỞI TẠO DANH SÁCH PERMISSIONS (Nếu chưa tồn tại)
            Permission pViewProd = getOrCreatePermission("VIEW_PRODUCT", "Xem thông tin sản phẩm");
            Permission pCreateProd = getOrCreatePermission("CREATE_PRODUCT", "Đăng bán sản phẩm mới");
            Permission pEditProd = getOrCreatePermission("EDIT_PRODUCT", "Chỉnh sửa thông tin sản phẩm của shop");

            Permission pOrder = getOrCreatePermission("CREATE_ORDER", "Đặt mua hàng tạo đơn hàng");
            Permission pManageOrder = getOrCreatePermission("MANAGE_ORDER", "Xử lý trạng thái đơn hàng (Duyệt/Hủy/Ship)");

            Permission pManageUser = getOrCreatePermission("MANAGE_USER", "Quản lý người dùng toàn hệ thống");

            // 2. KHỞI TẠO ROLE: BUYER (Người mua)
            if (roleRepository.findByName("BUYER").isEmpty()) {
                Role buyer = new Role();
                buyer.setName("BUYER");
                buyer.setDescription("Khách hàng mua sắm");
                buyer.getPermissions().add(pViewProd);
                buyer.getPermissions().add(pOrder);
                roleRepository.save(buyer);
                log.info("✔️ Đã tạo thành công Role: BUYER kèm quyền mặc định.");
            }

            // 3. KHỞI TẠO ROLE: SELLER (Người bán)
            if (roleRepository.findByName("SELLER").isEmpty()) {
                Role seller = new Role();
                seller.setName("SELLER");
                seller.setDescription("Đối tác kinh doanh, chủ gian hàng");
                seller.getPermissions().add(pViewProd);
                seller.getPermissions().add(pCreateProd);
                seller.getPermissions().add(pEditProd);
                seller.getPermissions().add(pManageOrder);
                roleRepository.save(seller);
                log.info("✔️ Đã tạo thành công Role: SELLER kèm quyền mặc định.");
            }

            // 4. KHỞI TẠO ROLE: ADMIN (Quản trị viên)
            if (roleRepository.findByName("ADMIN").isEmpty()) {
                Role admin = new Role();
                admin.setName("ADMIN");
                admin.setDescription("Quản trị viên tối cao của hệ thống");

                // Admin lấy toàn bộ tất cả các quyền trên
                Set<Permission> adminPermissions = new HashSet<>();
                adminPermissions.add(pViewProd);
                adminPermissions.add(pCreateProd);
                adminPermissions.add(pEditProd);
                adminPermissions.add(pOrder);
                adminPermissions.add(pManageOrder);
                adminPermissions.add(pManageUser);

                admin.setPermissions(adminPermissions);
                roleRepository.save(admin);
                log.info("✔️ Đã tạo thành công Role: ADMIN kèm trọn bộ đặc quyền.");
            }

            log.info("==> Hoàn tất tiến trình đồng bộ Roles & Permissions!");
        };
    }

    private Permission getOrCreatePermission(String name, String description) {
        return permissionRepository.findByName(name)
                .orElseGet(() -> {
                    Permission newPermission = new Permission(name, description);
                    Permission saved = permissionRepository.save(newPermission);
                    log.info(" ➕ Đã tạo mới Permission: {}", name);
                    return saved;
                });
    }
}

