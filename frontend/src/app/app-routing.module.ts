import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DangkyComponent } from './dangky/dangky.component';
import { KetquaComponent } from './ketqua/ketqua.component';
import { ThongtinComponent } from './thongtin/thongtin.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dangky', component: DangkyComponent },
  { path: 'ketqua', component: KetquaComponent },
  { path: 'thongtin', component: ThongtinComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
