import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DangnhapComponent } from './web_login/dangnhap/dangnhap.component';
import { DangkyComponent } from './web_login/dangky/dangky.component';
import { ThongtinComponent } from './web_login/thongtin/thongtin.component';
import { HttpClientModule } from '@angular/common/http';
import { NgModel } from '@angular/forms';
@NgModule({
  declarations: [
    AppComponent,
    DangnhapComponent,
    DangkyComponent,
    ThongtinComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
