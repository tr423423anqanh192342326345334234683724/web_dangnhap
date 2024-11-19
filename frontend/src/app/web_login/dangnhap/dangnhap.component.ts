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
    console.log('Sending:', {
      taikhoan: this.taikhoan,
      matkhau: this.matkhau
    });
    this.http.post('http://localhost:8080/api/khachhangs/dangnhap', {
      taikhoan: this.taikhoan,
      matkhau: this.matkhau
    }).subscribe((response: any) => {
      if (response && response.success) {
        console.log(response.message);
        this.router.navigate(['/thongtin']);
      } else {
        console.log(response.message);
      }
    }, error => {
      console.log('Lỗi khi kết nối đến server:', error);
    });
  }

}
