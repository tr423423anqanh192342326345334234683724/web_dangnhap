package com.example.backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KhachhangRepository extends JpaRepository<khachhang, Long> {
    khachhang findByTaikhoanAndMatkhau(String taikhoan, String matkhau);
    khachhang findByEmail(String email);
}
