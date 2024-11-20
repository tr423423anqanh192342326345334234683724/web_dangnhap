import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dangky',
  templateUrl: './dangky.component.html',
  styleUrls: ['./dangky.component.css']
})
export class DangkyComponent {
  constructor(private http: HttpClient, private router: Router) {}

  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  email: string = '';
  xacnhanemail: boolean = false;

  // Kiểm tra định dạng email hợp lệ
  kiemTraEmail(email: string): boolean {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
  }

  // Kiểm tra đăng ký
  kiemtradangky() {
    // Kiểm tra mật khẩu có khớp không
    if (this.password !== this.confirmPassword) {
      alert('Mật khẩu không khớp!');
      return;
    }

    // Kiểm tra email hợp lệ
    if (!this.kiemTraEmail(this.email)) {
      alert('Email không hợp lệ!');
      return;
    }

    const khachhang = {
      taiKhoan: this.username,
      matKhau: this.password,
      email: this.email,
    };

    // Gửi request đăng ký đến API
    this.http.post('http://localhost:8080/api/khachhangs/dangky', khachhang, { headers: { 'Content-Type': 'application/json' } })
      .subscribe((response: any) => {
        if(response.message === "Đăng ký thành công!") {
          alert("Đăng ký thành công!");
          this.router.navigate(['/dangnhap']);
        } else {
          alert(response.message);
        }
      });
  }
}
