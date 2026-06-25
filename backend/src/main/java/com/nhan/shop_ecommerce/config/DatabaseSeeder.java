package com.nhan.shop_ecommerce.config;

import com.nhan.shop_ecommerce.domain.*;
import com.nhan.shop_ecommerce.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class DatabaseSeeder implements CommandLineRunner {

    BannerRepository bannerRepository;
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    FlashSaleRepository flashSaleRepository;
    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedAdminUser();
        seedBanners();
        seedCategoriesAndProducts();
        seedFlashSale();
    }

    private void seedBanners() {
        if (bannerRepository.count() == 0) {
            Banner b1 = new Banner();
            b1.setImageUrl("https://images.unsplash.com/photo-1607082348824-0a96f2a4b9da?w=1200&h=400&fit=crop");
            b1.setTargetUrl("/sale1");
            b1.setDisplayOrder(1);
            b1.setActive(true);

            Banner b2 = new Banner();
            b2.setImageUrl("https://images.unsplash.com/photo-1607083206869-4c7672e72a8a?w=1200&h=400&fit=crop");
            b2.setTargetUrl("/sale2");
            b2.setDisplayOrder(2);
            b2.setActive(true);

            bannerRepository.saveAll(List.of(b1, b2));
            System.out.println("Seeded Banners!");
        }
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setDescription("Administrator role");

            Role user = new Role();
            user.setName("USER");
            user.setDescription("Default user role");

            roleRepository.saveAll(List.of(admin, user));
            System.out.println("Seeded Roles!");
        }
    }

    private void seedAdminUser() {
        if (!userRepository.existsByemail("admin@gmail.com")) {
            Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setFullName("Admin");
            admin.setPhoneNumber("0123456789");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(new HashSet<>(List.of(adminRole)));
            admin.setCreatedBy("system");

            userRepository.save(admin);
            System.out.println("Seeded Admin User!");
        }
    }

    private void seedCategoriesAndProducts() {
        if (categoryRepository.count() == 0) {
            // Seed Categories
            Category c1 = new Category();
            c1.setName("Thời Trang Nam");
            c1.setImageUrl("https://images.unsplash.com/photo-1490578474895-699cd4e2cf59?w=100&h=100&fit=crop");

            Category c2 = new Category();
            c2.setName("Điện Thoại & Phụ Kiện");
            c2.setImageUrl("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=100&h=100&fit=crop");

            Category c3 = new Category();
            c3.setName("Mẹ & Bé");
            c3.setImageUrl("https://images.unsplash.com/photo-1519689680058-324335c77eba?w=100&h=100&fit=crop");

            Category c4 = new Category();
            c4.setName("Đồng Hồ");
            c4.setImageUrl("https://images.unsplash.com/photo-1524805444758-089113d48a6d?w=100&h=100&fit=crop");

            categoryRepository.saveAll(List.of(c1, c2, c3, c4));
            System.out.println("Seeded Categories!");

            // Seed Products
            Product p1 = new Product();
            p1.setTitle("Áo Khoác Nam Thể Thao Mùa Đông Siêu Ấm");
            p1.setMainImage("https://images.unsplash.com/photo-1556821840-3a63f95609a7?w=400&h=400&fit=crop");
            p1.setActive(true);
            p1.setCategory(c1);
            p1.setCreatedBy("system");

            ProductVariant v1 = new ProductVariant();
            v1.setName("Đen, Size M");
            v1.setPrice(250000.0);
            v1.setStock(100);
            v1.setActive(true);
            v1.setProduct(p1);
            v1.setCreatedBy("system");

            ProductVariant v2 = new ProductVariant();
            v2.setName("Đỏ, Size L");
            v2.setPrice(350000.0);
            v2.setStock(50);
            v2.setActive(true);
            v2.setProduct(p1);
            v2.setCreatedBy("system");

            p1.setProductVariants(new HashSet<>(List.of(v1, v2)));

            Product p2 = new Product();
            p2.setTitle("Giày Sneaker Thể Thao Năng Động Đi Học");
            p2.setMainImage("https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400&h=400&fit=crop");
            p2.setActive(true);
            p2.setCategory(c1);
            p2.setCreatedBy("system");

            ProductVariant v3 = new ProductVariant();
            v3.setName("Trắng, 42");
            v3.setPrice(500000.0);
            v3.setStock(200);
            v3.setActive(true);
            v3.setProduct(p2);
            v3.setCreatedBy("system");

            p2.setProductVariants(new HashSet<>(List.of(v3)));

            Product p3 = new Product();
            p3.setTitle("Balo Laptop Chống Nước Ngăn Chứa Rộng Rãi");
            p3.setMainImage("https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=400&h=400&fit=crop");
            p3.setActive(true);
            p3.setCategory(c2);
            p3.setCreatedBy("system");

            ProductVariant v4 = new ProductVariant();
            v4.setName("Đen");
            v4.setPrice(199000.0);
            v4.setStock(150);
            v4.setActive(true);
            v4.setProduct(p3);
            v4.setCreatedBy("system");

            p3.setProductVariants(new HashSet<>(List.of(v4)));

            Product p4 = new Product();
            p4.setTitle("Kính Mát Thời Trang Gọng Vuông Cổ Điển");
            p4.setMainImage("https://images.unsplash.com/photo-1511499767150-a48a237f0083?w=400&h=400&fit=crop");
            p4.setActive(true);
            p4.setCategory(c4);
            p4.setCreatedBy("system");

            ProductVariant v5 = new ProductVariant();
            v5.setName("Gọng Đen");
            v5.setPrice(99000.0);
            v5.setStock(300);
            v5.setActive(true);
            v5.setProduct(p4);
            v5.setCreatedBy("system");

            p4.setProductVariants(new HashSet<>(List.of(v5)));

            productRepository.saveAll(List.of(p1, p2, p3, p4));
            System.out.println("Seeded Products and Variants!");
        }
    }

    private void seedFlashSale() {
        if (flashSaleRepository.count() == 0 && productRepository.count() > 0) {
            Product p1 = productRepository.findAll().get(0);
            ProductVariant pv1 = p1.getProductVariants().iterator().next();

            FlashSale flashSale = new FlashSale();
            flashSale.setCampaignName("Siêu Sale Giữa Tháng");
            flashSale.setStartTime(LocalDateTime.now().minusHours(1)); // Started 1 hour ago
            flashSale.setEndTime(LocalDateTime.now().plusHours(11));  // Ends in 11 hours
            flashSale.setStatus(true);

            FlashSaleItem fsi1 = new FlashSaleItem();
            fsi1.setFlashSale(flashSale);
            fsi1.setProductVariant(pv1);
            fsi1.setFlashSalePrice(pv1.getPrice() * 0.5); // 50% off
            fsi1.setFlashSaleStock(100);
            fsi1.setFlashSaleSold(45);

            flashSale.setFlashSaleItems(List.of(fsi1));

            flashSaleRepository.save(flashSale);
            System.out.println("Seeded Flash Sale!");
        }
    }
}
