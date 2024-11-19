import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dangky',
  templateUrl: './dangky.component.html',
  styleUrls: ['./dangky.component.css']
})
export class DangkyComponent {
  constructor(private http: HttpClient) {}

  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  email: string = '';

  kiemtradangky() {
    const formData = {
      taikhoan: (document.querySelector('input[name="username"]') as HTMLInputElement).value,
      matkhau: (document.querySelector('input[name="password"]') as HTMLInputElement).value,
      email: (document.querySelector('input[name="email"]') as HTMLInputElement).value
    };

    this.http.post('http://localhost:8080/api/khachhangs', formData).subscribe(response => {
      console.log('Đăng ký thành công', response);
    }, error => {
      console.error('Đăng ký thất bại', error);
    });
  }
}
