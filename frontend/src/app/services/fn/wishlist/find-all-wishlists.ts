/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseWishlistResponse } from '../../models/page-response-wishlist-response';

export interface FindAllWishlists$Params {
  page?: number;
  size?: number;
}

export function findAllWishlists(http: HttpClient, rootUrl: string, params?: FindAllWishlists$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseWishlistResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllWishlists.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseWishlistResponse>;
    })
  );
}

findAllWishlists.PATH = '/wishlists';
