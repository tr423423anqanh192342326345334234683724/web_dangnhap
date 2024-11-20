package com.example.backend;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface khachhangRepository extends JpaRepository<khachhang, Long> {
    Optional<khachhang> findByTaiKhoan(String taiKhoan);
    Optional<khachhang> findByActivationtoken(String activationtoken);
    khachhang save(khachhang khachhang);
}
