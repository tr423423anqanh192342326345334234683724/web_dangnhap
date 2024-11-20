package com.example.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/khachhangs")

public class khachhangController {

    @Autowired
    private khachhangService khachhangService;

    @Autowired
    private khachhangRepository khachhangRepository;

    @PostMapping("/kiemtradangnhap")
    public ResponseEntity<Map<String, String>> kiemTraDangNhap(@RequestBody Map<String, String> credentials) {
        String taiKhoan = credentials.get("taikhoan");
        String matKhau = credentials.get("matkhau");
        Map<String, String> response = new HashMap<>();
        try {
            boolean isAuthenticated = khachhangService.kiemTraDangNhap(taiKhoan, matKhau);
            if (isAuthenticated) {
                response.put("message", "Đăng nhập thành công!");
                response.put("userId", String.valueOf(khachhangService.layIdKhachHang(taiKhoan)));
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Sai tài khoản hoặc mật khẩu!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (IllegalStateException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @PostMapping("/dangky")
    public ResponseEntity<Map<String, String>> dangKy(@RequestBody khachhang khachhang) {
        Map<String, String> response = new HashMap<>();
        try {
            Optional<khachhang> existingKhachhangByTaiKhoan = khachhangRepository.findByTaiKhoan(khachhang.getTaiKhoan());
            if (existingKhachhangByTaiKhoan.isPresent()) {
                response.put("error", "Tài khoản đã tồn tại!");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            Optional<khachhang> existingKhachhangByEmail = khachhangRepository.findByEmail(khachhang.getEmail());
            if (existingKhachhangByEmail.isPresent()) {
                response.put("error", "Email đã tồn tại!");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            khachhangService.kiemTraDangKy(khachhang);
            response.put("message", "Đăng ký thành công! Vui lòng kiểm tra email để kích hoạt tài khoản trước khi đăng nhập!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Đăng ký thất bại!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/kichhoat")
    public ResponseEntity<Map<String, String>> xacNhanTaiKhoan(@RequestParam String token) {
        khachhangService.kichHoatTaiKhoan(token);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Kích hoạt tài khoản thành công!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/thongtinkhachhang")
    public ResponseEntity<?> layThongTinKhachHang(@RequestParam("id") long id) {
        khachhang khachhang = khachhangService.thongtincuanguoidangnhap(id);
        if (khachhang != null) {
            return ResponseEntity.ok(khachhang);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy khách hàng!");
        }
    }

}
