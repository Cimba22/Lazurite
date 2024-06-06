import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WishlistRoutingModule } from './wishlist-routing.module';
import { MainComponent } from './pages/main/main.component';
import { HeaderComponent } from './components/header/header.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';
import { ListComponent } from './components/list/list.component';
import { WishlistCardComponent } from './components/wishlist-card/wishlist-card.component';
import { MyGiftsComponent } from './pages/my-gifts/my-gifts.component';
import { ManageWishlistComponent } from './pages/manage-wishlist/manage-wishlist.component';


@NgModule({
  declarations: [
    MainComponent,
    HeaderComponent,
    NavbarComponent,
    WishlistComponent,
    ListComponent,
    WishlistCardComponent,
    MyGiftsComponent,
    ManageWishlistComponent
  ],
  imports: [
    CommonModule,
    WishlistRoutingModule
  ]
})
export class WishlistModule { }
