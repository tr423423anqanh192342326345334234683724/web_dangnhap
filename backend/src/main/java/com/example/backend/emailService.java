package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailService {
    @Autowired
    private JavaMailSender emailSender;

    public void guiEmailKichHoat(String to, String activationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Kích hoạt tài khoản");
        message.setText("Vui lòng nhấp vào liên kết sau để kích hoạt tài khoản của bạn: " + activationLink);
        emailSender.send(message);
    }
}
