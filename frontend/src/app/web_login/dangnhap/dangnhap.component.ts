import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dangnhap',
  templateUrl: './dangnhap.component.html',
  styleUrls: ['./dangnhap.component.css']
})
export class DangnhapComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient) { }

  taikhoan: string = '';
  matkhau: string = '';
  
  ngOnInit(): void {
  }

  kiemtradangnhap(){
    this.http.get(`http://localhost:8080/api/khachhangs/kiemtradangnhap?taikhoan=${this.taikhoan}&matkhau=${this.matkhau}`)
      .subscribe((response: any) => {
        if (response.message === "Đăng nhập thành công!") {
          alert("Đăng nhập thành công!");
          localStorage.setItem('userId', response.userId);
          this.router.navigate(['/thongtin']);
        } else {
          alert(response.message);
        }
      }, error => {
        console.log('Lỗi khi kết nối đến server:', error);
        alert("Sai tài khoản hoặc mật khẩu!");
      });
  }

}