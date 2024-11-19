import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DangnhapComponent } from './web_login/dangnhap/dangnhap.component';
import { DangkyComponent } from './web_login/dangky/dangky.component';
import { ThongtinComponent } from './web_login/thongtin/thongtin.component';

const routes: Routes = [
  { path: '', redirectTo: 'dangnhap', pathMatch: 'full' },
  { path: 'dangnhap', component: DangnhapComponent },
  { path: 'dangky', component: DangkyComponent },
  { path: 'thongtin', component: ThongtinComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  
})
export class AppRoutingModule { }
