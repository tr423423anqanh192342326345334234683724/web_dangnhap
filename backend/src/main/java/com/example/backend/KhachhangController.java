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


    @GetMapping("/kiemtradangnhap")
    public ResponseEntity<Map<String, String>> kiemTraDangNhap(@RequestParam("taikhoan") String taiKhoan, @RequestParam("matkhau") String matKhau) {
        boolean isAuthenticated = khachhangService.kiemTraDangNhap(taiKhoan, matKhau);
        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "Đăng nhập thành công!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Sai tài khoản hoặc mật khẩu!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @PostMapping("/dangky")
    public ResponseEntity<?> dangKy(@RequestBody khachhang khachhang) {
        try {
            khachhangService.kiemTraDangKy(khachhang);
            return ResponseEntity.ok("Đăng ký thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đăng ký thất bại!");
        }
    }

    @GetMapping("/kichhoat")
    public ResponseEntity<Map<String, String>> xacNhanTaiKhoan(@RequestParam String token) {
        khachhangService.kichHoatTaiKhoan(token);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Kích hoạt tài khoản thành công!");
        return ResponseEntity.ok(response);
    }


    
}
