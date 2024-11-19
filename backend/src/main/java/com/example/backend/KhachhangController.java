package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/khachhangs")
public class KhachhangController {

    @Autowired
    private KhachhangService khachhangService;

    @GetMapping
    public List<khachhang> getAllKhachhangs() {
        return khachhangService.getAllKhachhangs();
    }

    @GetMapping("/{id}")
    public khachhang getKhachhangById(@PathVariable String id) {
        return khachhangService.getKhachhangById(id);
    }

    @PostMapping
    public khachhang createKhachhang(@RequestBody khachhang khachhang) {
        khachhang savedKhachhang = khachhangService.saveKhachhang(khachhang);
        khachhangService.sendActivationEmail(savedKhachhang);
        return savedKhachhang;
    }

    @DeleteMapping("/{id}")
    public void deleteKhachhang(@PathVariable String id) {
        khachhangService.deleteKhachhang(id);
    }

    @PostMapping("/activate/{id}")
    public void activateKhachhang(@PathVariable String id) {
        khachhangService.activateKhachhang(id);
    }

    @PostMapping("/kiemtradangnhap")
    public ResponseEntity<Map<String, Object>> kiemtradangnhap(@RequestBody khachhang khachhang) {
        Map<String, Object> response = khachhangService.kiemtradangnhap(khachhang.getTaikhoan(), khachhang.getMatkhau());
        return ResponseEntity.ok(response);
    }
}
