package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.Wishlist;
import com.cimba.lazurite.entity.dto.WishlistRequest;
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
}
