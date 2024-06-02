import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WishlistRoutingModule } from './wishlist-routing.module';
import { MainComponent } from './pages/main/main.component';
import { HeaderComponent } from './components/header/header.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import { GiftComponent } from './pages/gift/gift.component';



@NgModule({
  declarations: [
    MainComponent,
    HeaderComponent,
    NavbarComponent,
    GiftComponent
  ],
  imports: [
    CommonModule,
    WishlistRoutingModule,
    FaIconComponent
  ]
})
export class WishlistModule { }
