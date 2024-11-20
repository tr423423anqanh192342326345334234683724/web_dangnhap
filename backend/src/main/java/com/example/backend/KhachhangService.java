package com.example.backend;

import java.util.Optional;
import java.util.UUID;

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
            khachhang khachhang = kh.get();
            if (!khachhang.getActive()) {
                throw new IllegalStateException("Tài khoản chưa được xác minh email!");
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(matKhau, khachhang.getMatKhau());
        }
        return false;
    }

    public khachhang kiemTraDangKy(khachhang khachhang) {
        // Kiểm tra tài khoản đã tồn tại
        Optional<khachhang> existingKhachhangByTaiKhoan = khachhangRepository.findByTaiKhoan(khachhang.getTaiKhoan());
        if (existingKhachhangByTaiKhoan.isPresent()) {
            throw new IllegalStateException("Tài khoản đã tồn tại!");
        }

        // Kiểm tra email đã tồn tại
        Optional<khachhang> existingKhachhangByEmail = khachhangRepository.findByEmail(khachhang.getEmail());
        if (existingKhachhangByEmail.isPresent()) {
            throw new IllegalStateException("Email đã tồn tại!");
        }

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

    public khachhang thongtincuanguoidangnhap(long id) {
        Optional<khachhang> kh = khachhangRepository.findById(id);
        return kh.get();
    }

    public long layIdKhachHang(String taiKhoan) {
        Optional<khachhang> kh = khachhangRepository.findByTaiKhoan(taiKhoan);
        return kh.map(khachhang -> khachhang.getId()).orElse(0L);
    }
    public boolean kiemTraTrangThaiTaiKhoan(String taiKhoan) {
        Optional<khachhang> kh = khachhangRepository.findByTaiKhoan(taiKhoan);
        return kh.map(khachhang -> khachhang.getActive()).orElse(false);
    }
}
