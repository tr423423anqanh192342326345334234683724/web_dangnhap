package com.example.backend;

import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class khachhangService {

    @Autowired
    private khachhangRepository khachhangRepository;

    @Autowired
    private emailService emailService;

    public boolean kiemTraDangNhap(String taiKhoan, String matKhau) {
        Optional<khachhang> kh = khachhangRepository.findByTaiKhoan(taiKhoan);
        if (kh.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(matKhau, kh.get().getMatKhau());
        }
        return false;
    }
    public khachhang kiemTraDangKy(khachhang khachhang) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        khachhang.setMatKhau(encoder.encode(khachhang.getMatKhau()));  // Mã hóa mật khẩu
        khachhang.setActivationToken(UUID.randomUUID().toString());
        khachhangRepository.save(khachhang);
        emailService.guiEmailKichHoat(khachhang.getEmail(), "http://localhost:8080/api/khachhangs/kichhoat?token=" + khachhang.getActivationToken());
        return khachhang;
    }
    public void kichHoatTaiKhoan(String token) {
        Optional<khachhang> optionalKhachhang = khachhangRepository.findByActivationtoken(token);
        if (optionalKhachhang.isPresent()) {
            khachhang khachhang = optionalKhachhang.get();

            khachhang.setActive(true);
            khachhang.setActivationToken(null); // Xóa token sau khi kích hoạt
            khachhangRepository.save(khachhang);
        } else {
            throw new IllegalStateException("Token không hợp lệ.");
        }
    }
}
