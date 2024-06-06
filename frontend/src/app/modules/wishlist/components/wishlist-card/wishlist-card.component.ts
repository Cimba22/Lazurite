import {Component, Input} from '@angular/core';
import {WishlistResponse} from "../../../../services/models/wishlist-response";

@Component({
  selector: 'app-wishlist-card',
  templateUrl: './wishlist-card.component.html',
  styleUrls: ['./wishlist-card.component.css']
})
export class WishlistCardComponent {
  get wishlistCover(): string | undefined {
    if(this._wishlist.image){
      return 'data:image/jpg;base64, ' + this.wishlist.image;
    }
    return this._wishlistCover;
  }
  get wishlist(): WishlistResponse {
    return this._wishlist;
  }

  @Input()
  set wishlist(value: WishlistResponse) {
    this._wishlist = value;
  }
  private _wishlist: WishlistResponse = {};
  private _wishlistCover: string | undefined;

}
