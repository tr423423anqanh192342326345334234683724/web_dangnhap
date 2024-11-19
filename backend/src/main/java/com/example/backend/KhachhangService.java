package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class KhachhangService {

    @Autowired
    private KhachhangRepository khachhangRepository;

    @Autowired
    private JavaMailSender mailSender;

    public List<khachhang> getAllKhachhangs() {
        return khachhangRepository.findAll();
    }

    public khachhang getKhachhangById(String id) {
        try {
            Long khachhangId = Long.parseLong(id);
            return khachhangRepository.findById(khachhangId).orElse(null);
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu id không phải là số
            System.out.println("ID không hợp lệ: " + id);
            return null;
        }
    }

    public khachhang saveKhachhang(khachhang khachhang) {
        return khachhangRepository.save(khachhang);
    }

    public void deleteKhachhang(String id) {
        khachhangRepository.deleteById(Long.parseLong(id));
    }

    public void activateKhachhang(String id) {
        khachhang kh = khachhangRepository.findById(Long.parseLong(id)).orElse(null);
        if (kh != null) {
            kh.setActive(true);
            khachhangRepository.save(kh);
        }
    }
    public Map<String, Object> dangnhap(String taikhoan, String matkhau) {
        Map<String, Object> response = new HashMap<>();
        khachhang kh = khachhangRepository.findByTaikhoanAndMatkhau(taikhoan, matkhau);
        if (kh != null) {
            sendConfirmationEmail(kh);
            response.put("success", true);
            response.put("message", "Đăng nhập thành công");
            response.put("khachhang", kh);
        } else {
            response.put("success", false);
            response.put("message", "Sai tài khoản hoặc mật khẩu");
        }
        return response;
    }

    private void sendConfirmationEmail(khachhang kh) {
        String toAddress = kh.getEmail();
        String subject = "Xác nhận đăng nhập";
        String content = "Vui lòng nhấp vào liên kết sau để xác nhận đăng nhập: "
                + "http://localhost:8080/api/khachhangs/confirm?email=" + kh.getEmail();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendActivationEmail(khachhang kh) {
        String toAddress = kh.getEmail();
        String subject = "Kích hoạt tài khoản của bạn";
        String content = "Vui lòng nhấp vào liên kết sau để kích hoạt tài khoản của bạn: "
                + "http://localhost:8080/api/khachhangs/activate/" + kh.getId();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
