/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findAllArchivedGifts } from '../fn/gift/find-all-archived-gifts';
import { FindAllArchivedGifts$Params } from '../fn/gift/find-all-archived-gifts';
import { findAllGifts } from '../fn/gift/find-all-gifts';
import { FindAllGifts$Params } from '../fn/gift/find-all-gifts';
import { findAllGiftsByOwner } from '../fn/gift/find-all-gifts-by-owner';
import { FindAllGiftsByOwner$Params } from '../fn/gift/find-all-gifts-by-owner';
import { findGiftById } from '../fn/gift/find-gift-by-id';
import { FindGiftById$Params } from '../fn/gift/find-gift-by-id';
import { GiftResponse } from '../models/gift-response';
import { PageResponseGiftResponse } from '../models/page-response-gift-response';
import { saveGift } from '../fn/gift/save-gift';
import { SaveGift$Params } from '../fn/gift/save-gift';

@Injectable({ providedIn: 'root' })
export class GiftService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllGifts()` */
  static readonly FindAllGiftsPath = '/gifts';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllGifts()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGifts$Response(params?: FindAllGifts$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseGiftResponse>> {
    return findAllGifts(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllGifts$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGifts(params?: FindAllGifts$Params, context?: HttpContext): Observable<PageResponseGiftResponse> {
    return this.findAllGifts$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseGiftResponse>): PageResponseGiftResponse => r.body)
    );
  }

  /** Path part for operation `saveGift()` */
  static readonly SaveGiftPath = '/gifts';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveGift()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveGift$Response(params: SaveGift$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveGift(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveGift$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveGift(params: SaveGift$Params, context?: HttpContext): Observable<number> {
    return this.saveGift$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findGiftById()` */
  static readonly FindGiftByIdPath = '/gifts/{gift-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findGiftById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findGiftById$Response(params: FindGiftById$Params, context?: HttpContext): Observable<StrictHttpResponse<GiftResponse>> {
    return findGiftById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findGiftById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findGiftById(params: FindGiftById$Params, context?: HttpContext): Observable<GiftResponse> {
    return this.findGiftById$Response(params, context).pipe(
      map((r: StrictHttpResponse<GiftResponse>): GiftResponse => r.body)
    );
  }

  /** Path part for operation `findAllGiftsByOwner()` */
  static readonly FindAllGiftsByOwnerPath = '/gifts/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllGiftsByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGiftsByOwner$Response(params?: FindAllGiftsByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseGiftResponse>> {
    return findAllGiftsByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllGiftsByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGiftsByOwner(params?: FindAllGiftsByOwner$Params, context?: HttpContext): Observable<PageResponseGiftResponse> {
    return this.findAllGiftsByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseGiftResponse>): PageResponseGiftResponse => r.body)
    );
  }

  /** Path part for operation `findAllArchivedGifts()` */
  static readonly FindAllArchivedGiftsPath = '/gifts/archived';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllArchivedGifts()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllArchivedGifts$Response(params?: FindAllArchivedGifts$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseGiftResponse>> {
    return findAllArchivedGifts(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllArchivedGifts$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllArchivedGifts(params?: FindAllArchivedGifts$Params, context?: HttpContext): Observable<PageResponseGiftResponse> {
    return this.findAllArchivedGifts$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseGiftResponse>): PageResponseGiftResponse => r.body)
    );
  }

}
