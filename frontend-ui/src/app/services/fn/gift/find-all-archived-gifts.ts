/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseGiftResponse } from '../../models/page-response-gift-response';

export interface FindAllArchivedGifts$Params {
  page?: number;
  size?: number;
}

export function findAllArchivedGifts(http: HttpClient, rootUrl: string, params?: FindAllArchivedGifts$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseGiftResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllArchivedGifts.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseGiftResponse>;
    })
  );
}

findAllArchivedGifts.PATH = '/gifts/archived';
