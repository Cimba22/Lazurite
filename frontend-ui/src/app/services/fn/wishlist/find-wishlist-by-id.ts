/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { WishlistResponse } from '../../models/wishlist-response';

export interface FindWishlistById$Params {
  'wishlist-id': number;
}

export function findWishlistById(http: HttpClient, rootUrl: string, params: FindWishlistById$Params, context?: HttpContext): Observable<StrictHttpResponse<WishlistResponse>> {
  const rb = new RequestBuilder(rootUrl, findWishlistById.PATH, 'get');
  if (params) {
    rb.path('wishlist-id', params['wishlist-id'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<WishlistResponse>;
    })
  );
}

findWishlistById.PATH = '/wishlists/{wishlist-id}';
