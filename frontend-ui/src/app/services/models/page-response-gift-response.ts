/* tslint:disable */
/* eslint-disable */
import { GiftResponse } from '../models/gift-response';
export interface PageResponseGiftResponse {
  content?: Array<GiftResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
