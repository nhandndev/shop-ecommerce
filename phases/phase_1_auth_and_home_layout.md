# Phase 1: Authentication & Home Page Layout

## Thời gian hoàn thành
- Tháng 06/2026

## Mục tiêu của Phase 1
Xây dựng nền tảng cơ bản cho hệ thống E-commerce, bao gồm luồng xác thực người dùng và thiết kế kiến trúc động (Strategy Pattern) cho dữ liệu trang chủ.

## Chi tiết các tính năng đã hoàn thành

### 1. Backend (Spring Boot)
- **Authentication**: Hoàn thiện API Đăng nhập và Đăng ký.
- **Home Page (Strategy Pattern)**:
  - Thiết kế kiến trúc `HomeSectionHandler` interface để dễ dàng mở rộng các phần của trang chủ.
  - Implement các handler cụ thể trả về dữ liệu tương ứng:
    - `BannerCarouselHandler`: Trả về danh sách Banner quảng cáo.
    - `QuickCategoriesHandler`: Trả về danh sách các danh mục mua sắm nhanh.
    - `FlashSaleHandler`: Trả về chiến dịch Flash Sale đang diễn ra kèm danh sách sản phẩm, trạng thái thời gian và số lượng.
    - `RecommendGrid`: Trả về danh sách các sản phẩm gợi ý dựa trên lượt cập nhật mới nhất.

### 2. Frontend (React + Tailwind CSS)
- **Luồng Authentication**: 
  - Giao diện Login và Register.
  - Tích hợp `AuthContext` và `axiosClient` để xử lý trạng thái đăng nhập, lưu token.
  - Cấu hình `ProtectedRoute` trong `App.jsx` để bảo vệ các route yêu cầu đăng nhập.
- **Giao diện Trang chủ (Home Page)**:
  - Dựng giao diện đẹp mắt bằng Tailwind CSS.
  - Sử dụng Mock Data mô phỏng chính xác cấu trúc dữ liệu trả về từ các Strategy Handler của Backend.
  - Hiển thị đầy đủ 4 section: Banner Carousel, Quick Categories, Flash Sale, Recommend Grid.
  - Áp dụng các thư viện icon như `lucide-react` để hoàn thiện UI.

## Ghi chú cho Phase tiếp theo (Phase 2)
- Khi bắt đầu Phase 2, developer có thể đọc `README.md` để lấy context chung về cấu trúc thư mục, stack công nghệ và cách chạy dự án.
- **Mục tiêu gợi ý cho Phase 2**: Tích hợp API gọi dữ liệu thật từ Backend cho trang chủ (thay thế Mock Data ở `Home.jsx`), xây dựng giỏ hàng (Cart) hoặc trang chi tiết sản phẩm (Product Detail).
