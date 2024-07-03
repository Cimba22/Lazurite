import {Component, OnInit} from '@angular/core';
import {PageResponseGiftResponse} from "../../../../services/models/page-response-gift-response";
import {GiftService} from "../../../../services/services/gift.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gifts-list',
  templateUrl: './gifts-list.component.html',
  styleUrls: ['./gifts-list.component.css']
})
export class GiftsListComponent implements OnInit{
  giftResponse: PageResponseGiftResponse = {};
  page = 0;
  size = 4;
  pages: any = [];

  constructor(
    private giftService: GiftService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllGiftsByOwner();
  }

  private findAllGiftsByOwner() {
    this.giftService.findAllGiftsByOwner({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (gifts) => {
        this.giftResponse = gifts;
        this.pages = Array(this.giftResponse.totalPages)
          .fill(0)
          .map((x, i) => i);
      }
    })
  }


  gotToPage(page: number) {
    this.page = page;
    this.findAllGiftsByOwner();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllGiftsByOwner();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllGiftsByOwner();
  }

  goToLastPage() {
    this.page = this.giftResponse.totalPages as number - 1;
    this.findAllGiftsByOwner();
  }

  goToNextPage() {
    this.page++;
    this.findAllGiftsByOwner();
  }

  get isLastPage() {
    return this.page === this.giftResponse.totalPages as number - 1;
  }
}
