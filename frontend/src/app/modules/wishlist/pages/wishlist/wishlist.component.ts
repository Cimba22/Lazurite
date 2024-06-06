import { Component, OnInit } from '@angular/core';
import {PageResponseWishlistResponse} from "../../../../services/models/page-response-wishlist-response";
import {WishlistService} from "../../../../services/services/wishlist.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit{
  wishlistResponse: PageResponseWishlistResponse = {};
  page = 0;
  size = 4;
  pages: any = [];

  constructor(
    private wishlistService: WishlistService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllWishlistsByOwner();
  }

  private findAllWishlistsByOwner() {
    this.wishlistService.findAllWishlistsByOwner({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (wishlists) => {
        this.wishlistResponse = wishlists;
        this.pages = Array(this.wishlistResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      }
    })
  }

  gotToPage(page: number) {
    this.page = page;
    this.findAllWishlistsByOwner();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllWishlistsByOwner();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllWishlistsByOwner();
  }

  goToLastPage() {
    this.page = this.wishlistResponse.totalPages as number - 1;
    this.findAllWishlistsByOwner();
  }

  goToNextPage() {
    this.page++;
    this.findAllWishlistsByOwner();
  }

  get isLastPage() {
    return this.page === this.wishlistResponse.totalPages as number - 1;
  }
}
