/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findAllWishlists } from '../fn/wishlist/find-all-wishlists';
import { FindAllWishlists$Params } from '../fn/wishlist/find-all-wishlists';
import { findAllWishlistsByOwner } from '../fn/wishlist/find-all-wishlists-by-owner';
import { FindAllWishlistsByOwner$Params } from '../fn/wishlist/find-all-wishlists-by-owner';
import { findWishlistById } from '../fn/wishlist/find-wishlist-by-id';
import { FindWishlistById$Params } from '../fn/wishlist/find-wishlist-by-id';
import { PageResponseWishlistResponse } from '../models/page-response-wishlist-response';
import { saveWishlist } from '../fn/wishlist/save-wishlist';
import { SaveWishlist$Params } from '../fn/wishlist/save-wishlist';
import { uploadWishlistCoverPicture } from '../fn/wishlist/upload-wishlist-cover-picture';
import { UploadWishlistCoverPicture$Params } from '../fn/wishlist/upload-wishlist-cover-picture';
import { WishlistResponse } from '../models/wishlist-response';

@Injectable({ providedIn: 'root' })
export class WishlistService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllWishlists()` */
  static readonly FindAllWishlistsPath = '/wishlists';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllWishlists()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllWishlists$Response(params?: FindAllWishlists$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseWishlistResponse>> {
    return findAllWishlists(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllWishlists$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllWishlists(params?: FindAllWishlists$Params, context?: HttpContext): Observable<PageResponseWishlistResponse> {
    return this.findAllWishlists$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseWishlistResponse>): PageResponseWishlistResponse => r.body)
    );
  }

  /** Path part for operation `saveWishlist()` */
  static readonly SaveWishlistPath = '/wishlists';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveWishlist()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveWishlist$Response(params: SaveWishlist$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveWishlist(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveWishlist$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveWishlist(params: SaveWishlist$Params, context?: HttpContext): Observable<number> {
    return this.saveWishlist$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `uploadWishlistCoverPicture()` */
  static readonly UploadWishlistCoverPicturePath = '/wishlists/cover/{wishlist-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadWishlistCoverPicture()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadWishlistCoverPicture$Response(params: UploadWishlistCoverPicture$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadWishlistCoverPicture(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadWishlistCoverPicture$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadWishlistCoverPicture(params: UploadWishlistCoverPicture$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadWishlistCoverPicture$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `findWishlistById()` */
  static readonly FindWishlistByIdPath = '/wishlists/{wishlist-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findWishlistById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findWishlistById$Response(params: FindWishlistById$Params, context?: HttpContext): Observable<StrictHttpResponse<WishlistResponse>> {
    return findWishlistById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findWishlistById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findWishlistById(params: FindWishlistById$Params, context?: HttpContext): Observable<WishlistResponse> {
    return this.findWishlistById$Response(params, context).pipe(
      map((r: StrictHttpResponse<WishlistResponse>): WishlistResponse => r.body)
    );
  }

  /** Path part for operation `findAllWishlistsByOwner()` */
  static readonly FindAllWishlistsByOwnerPath = '/wishlists/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllWishlistsByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllWishlistsByOwner$Response(params?: FindAllWishlistsByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseWishlistResponse>> {
    return findAllWishlistsByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllWishlistsByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllWishlistsByOwner(params?: FindAllWishlistsByOwner$Params, context?: HttpContext): Observable<PageResponseWishlistResponse> {
    return this.findAllWishlistsByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseWishlistResponse>): PageResponseWishlistResponse => r.body)
    );
  }

}
