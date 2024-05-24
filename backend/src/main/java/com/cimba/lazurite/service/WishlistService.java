package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.Wishlist;
import com.cimba.lazurite.entity.dto.WishlistRequest;
import com.cimba.lazurite.entity.dto.WishlistResponse;
import com.cimba.lazurite.repository.WishlistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;

    public Long save(WishlistRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Wishlist wishlist = wishlistMapper.toWishlist(request);
        wishlist.setOwner(user);
        return wishlistRepository.save(wishlist).getId();
    }

    public WishlistResponse findById(Long id) {
        return wishlistRepository.findById(id)
                .map(wishlistMapper::toWishlistResponse)
                .orElseThrow(() -> new EntityNotFoundException("No wishlist found with the ID: " + id));
    }
}
