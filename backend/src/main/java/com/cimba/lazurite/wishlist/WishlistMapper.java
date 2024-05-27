package com.cimba.lazurite.wishlist;

import com.cimba.lazurite.entity.Wishlist;
import org.springframework.stereotype.Service;

@Service
public class WishlistMapper {
    public Wishlist toWishlist(WishlistRequest request) {
        return Wishlist.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .image(request.image())
                .build();
    }

    public WishlistResponse toWishlistResponse(Wishlist wishlist) {
        return WishlistResponse.builder()
                .id(wishlist.getId())
                .name(wishlist.getName())
                .description(wishlist.getDescription())
                .owner(wishlist.getOwner().getLogin())
                //.image()
                .build();
    }
}
