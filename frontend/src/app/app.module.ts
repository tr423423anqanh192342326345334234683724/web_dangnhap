import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DangkyComponent } from './dangky/dangky.component';
import { KetquaComponent } from './ketqua/ketqua.component';
import { ThongtinComponent } from './thongtin/thongtin.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dangky', component: DangkyComponent },
  { path: 'ketqua', component: KetquaComponent },
  { path: 'thongtin', component: ThongtinComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DangkyComponent,
    KetquaComponent,
    ThongtinComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
