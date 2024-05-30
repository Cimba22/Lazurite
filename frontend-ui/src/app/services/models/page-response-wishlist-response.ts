/* tslint:disable */
/* eslint-disable */
import { WishlistResponse } from '../models/wishlist-response';
export interface PageResponseWishlistResponse {
  content?: Array<WishlistResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
