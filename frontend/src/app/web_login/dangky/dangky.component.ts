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

  taiKhoan: string = '';
  matKhau: string = '';
  xacNhanMatKhau: string = '';
  email: string = '';
  errorMessage: string = '';

  kiemTraEmail(email: string): boolean {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
  }

  kiemtradangky() {
    if (this.matKhau !== this.xacNhanMatKhau) {
      this.errorMessage = 'Mật khẩu không khớp!';
      return;
    }

    if (!this.kiemTraEmail(this.email)) {
      this.errorMessage = 'Email không hợp lệ!';
      return;
    }

    const khachhang = {
      taiKhoan: this.taiKhoan,
      matKhau: this.matKhau,
      email: this.email
    };

    this.http.post('http://localhost:8080/api/khachhangs/dangky', khachhang)
      .subscribe(
        (response: any) => {
          console.log('Phản hồi:', response);
          if (response.message) {
            alert(response.message);
            this.router.navigate(['/dangnhap']);
          }
        },
        error => {
          console.error('Lỗi:', error);
          if (error.error && error.error.error) {
            this.errorMessage = error.error.error;
          } else {
            this.errorMessage = 'Có lỗi xảy ra, vui lòng thử lại!';
          }
        }
      );
  }
}
