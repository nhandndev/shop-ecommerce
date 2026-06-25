# Shop E-commerce

Hệ thống E-commerce được phát triển với kiến trúc tách biệt Frontend và Backend, hỗ trợ đầy đủ các tính năng cơ bản của một sàn thương mại điện tử.

## Stack Công Nghệ
- **Backend:** Java Spring Boot, Hibernate/JPA
- **Database:** PostgreSQL (chạy qua Docker Compose)
- **Frontend:** React.js, Tailwind CSS, Vite

## Cấu Trúc Dự Án
- `/backend`: Mã nguồn Spring Boot. Tổ chức theo Modular Monolith, áp dụng Strategy Pattern cho các tính năng linh hoạt (như tải dữ liệu cho Home Page).
- `/frontend`: Mã nguồn React. Sử dụng Context API cho State Management, Axios cho Network Request, React Router cho Điều hướng.

## Tiến Độ & Lịch Sử Phát Triển (Phases)
Để xem lịch sử chi tiết những gì đã làm, vui lòng tham khảo các tài liệu trong thư mục [`/phases`](file:///Users/lilnhan/Documents/GitHub/shop-ecommerce/phases).
- **[Phase 1: Authentication & Home Page Layout](file:///Users/lilnhan/Documents/GitHub/shop-ecommerce/phases/phase_1_auth_and_home_layout.md)**: Đã hoàn thiện Đăng nhập, Đăng ký và kiến trúc Strategy Pattern cho Trang chủ kèm Mock UI.

## Cách Chạy Dự Án
### Chạy Database (PostgreSQL)
```bash
docker-compose up -d
```
### Chạy Backend
```bash
cd backend
./mvnw spring-boot:run
```
### Chạy Frontend
```bash
cd frontend
npm install
npm run dev
```
