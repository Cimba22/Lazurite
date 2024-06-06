import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {WishlistComponent} from "./pages/wishlist/wishlist.component";
import {MyGiftsComponent} from "./pages/my-gifts/my-gifts.component";
import {ManageWishlistComponent} from "./pages/manage-wishlist/manage-wishlist.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: WishlistComponent
      },
      {
        path: 'my-gifts',
        component: MyGiftsComponent
      },
      {
        path: 'manage',
        component: ManageWishlistComponent
      },
      {
        path: 'manage/:wishlistId',
        component: ManageWishlistComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WishlistRoutingModule { }
