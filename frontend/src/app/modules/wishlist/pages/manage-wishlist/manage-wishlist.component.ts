import { Component } from '@angular/core';
import {WishlistRequest} from "../../../../services/models/wishlist-request";
import {WishlistService} from "../../../../services/services/wishlist.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-manage-wishlist',
  templateUrl: './manage-wishlist.component.html',
  styleUrls: ['./manage-wishlist.component.css']
})
export class ManageWishlistComponent {
  wishlistRequest: WishlistRequest = {description: "", name: ""};
  errorMsg: Array<string> = [];
  selectedWishlistCover: any;
  selectedPicture: string | undefined;

  constructor(
    private wishlistService: WishlistService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  onFileSelected(event: any) {
    this.selectedWishlistCover = event.target.files[0];
    console.log(this.selectedWishlistCover);
    if (this.selectedWishlistCover) {
      const reader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string;
      };
      reader.readAsDataURL(this.selectedWishlistCover);
    }

  }

  saveBook() {
    this.wishlistService.saveWishlist({
      body: this.wishlistRequest
    }).subscribe({
      next: (wishlistId) => {
        this.wishlistService.uploadWishlistCoverPicture({
          'wishlist-id': wishlistId,
          body: {
            file: this.selectedWishlistCover
          }
        }).subscribe({
          next: () => {
            this.router.navigate(['wishlists']);
          }
        })
      },
      error: (err) => {
        this.errorMsg = err.error.validationErrors;
      }
    })
  }
}
