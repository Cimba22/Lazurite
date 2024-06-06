import {Component, EventEmitter, Input, Output} from '@angular/core';
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
    return 'https://source.unsplash.com/user/c_v_r/1900x800';
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

  @Output() private delete: EventEmitter<WishlistResponse> = new EventEmitter<WishlistResponse>();
  @Output() private edit: EventEmitter<WishlistResponse> = new EventEmitter<WishlistResponse>();
  @Output() private details: EventEmitter<WishlistResponse> = new EventEmitter<WishlistResponse>();
  @Output() private share: EventEmitter<WishlistResponse> = new EventEmitter<WishlistResponse>();

  onShowDetails() {
    this.details.emit(this._wishlist);
  }

  onEdit() {
    this.edit.emit(this._wishlist);
  }

  onShare() {
    this.share.emit(this._wishlist);
  }

  onDelete() {
    this.delete.emit(this._wishlist);
  }
}
