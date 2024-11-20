import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-thongtin',
  templateUrl: './thongtin.component.html',
  styleUrls: ['./thongtin.component.css']
})
export class ThongtinComponent implements OnInit {
  khachhang: any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    const id = localStorage.getItem('userId');
    if (id) {
      this.http.get(`http://localhost:8080/api/khachhangs/thongtinkhachhang?id=${id}`).subscribe(
        data => {
          console.log('Dữ liệu khách hàng:', data);
          this.khachhang = data;
        },
        error => console.error('Có lỗi xảy ra!', error)
      );
    } else {
      console.error('Không tìm thấy ID người dùng!');
    }
  }
}
