package com.nhan.shop_ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // Luồng thành công chung
    SUCCESS(1000, "Thành công", HttpStatus.OK),

    // Hệ thống & Hạ tầng (Lỗi bảo mật bảo trì)
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi chưa phân loại hệ thống", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(8888, "Khóa cấu hình thông báo lỗi không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_INPUT(7777, "Dữ liệu đầu vào không hợp lệ", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1001, "Xác thực không thành công hoặc phiên đăng nhập hết hạn", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "Bạn không có quyền truy cập chức năng này", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND(1003,"Không tìm thấy role trong hệ thống",HttpStatus.BAD_REQUEST),
    TOKEN_CANNOT_CREATE(1004,"Không thể tạo token ",HttpStatus.BAD_REQUEST),
    TOKEN_INVALID(1005,"Token Bị Lỗi",HttpStatus.BAD_REQUEST),

    // Nghiệp vụ phân hệ Người dùng (Users & OTP)
    USER_EXISTED(2001, "Email này đã được sử dụng trên hệ thống", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_EXISTED(2002, "Số điện thoại này đã được sử dụng trên hệ thống", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(2003, "Không tìm thấy thông tin tài khoản", HttpStatus.NOT_FOUND),
    USER_NOT_ENABLED(2004, "Tài khoản của bạn chưa được kích hoạt qua mã OTP", HttpStatus.FORBIDDEN),
    INVALID_OTP(2005, "Mã xác thực OTP không chính xác hoặc đã hết hạn", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(2006, "Mật khẩu đăng nhập không chính xác", HttpStatus.BAD_REQUEST),

    // Nghiệp vụ phân hệ Sản phẩm & Kho hàng (Products & Inventory)
    PRODUCT_NOT_FOUND(3001, "Sản phẩm không tồn tại", HttpStatus.NOT_FOUND),
    VARIANT_NOT_FOUND(3002, "Biến thể sản phẩm (size/màu) không tồn tại", HttpStatus.NOT_FOUND),
    OUT_OF_STOCK(3003, "Sản phẩm đã hết hàng trong kho", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

}
