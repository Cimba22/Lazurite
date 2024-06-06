/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { GiftResponse } from '../../models/gift-response';

export interface FindGiftById$Params {
  'gift-id': number;
}

export function findGiftById(http: HttpClient, rootUrl: string, params: FindGiftById$Params, context?: HttpContext): Observable<StrictHttpResponse<GiftResponse>> {
  const rb = new RequestBuilder(rootUrl, findGiftById.PATH, 'get');
  if (params) {
    rb.path('gift-id', params['gift-id'], {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<GiftResponse>;
    })
  );
}

findGiftById.PATH = '/gifts/{gift-id}';
