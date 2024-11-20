package com.example.backend;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface khachhangRepository extends JpaRepository<khachhang, Long> {
    Optional<khachhang> findByTaiKhoan(String taiKhoan);
    Optional<khachhang> findByActivationtoken(String activationtoken);
    Optional<khachhang> findById(long id);
    Optional<khachhang> findByEmail(String email);
    khachhang save(khachhang khachhang);
}
