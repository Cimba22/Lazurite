package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.entity.Wishlist;
import com.cimba.lazurite.entity.dto.WishlistRequest;
import com.cimba.lazurite.entity.dto.WishlistResponse;
import com.cimba.lazurite.repository.WishlistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cimba.lazurite.service.WishlistSpecification.withOwnerId;

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

    public PageResponse<WishlistResponse> findAllWishlists(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Wishlist> wishlists = wishlistRepository.findAllDisplayableWishlists(pageable, user.getIdUser());
        List<WishlistResponse> wishlistResponse = wishlists.stream()
                .map(wishlistMapper::toWishlistResponse)
                .toList();
        return new PageResponse<>(
                wishlistResponse,
                wishlists.getNumber(),
                wishlists.getSize(),
                wishlists.getTotalElements(),
                wishlists.getTotalPages(),
                wishlists.isFirst(),
                wishlists.isLast()
        );
    }

    public PageResponse<WishlistResponse> findAllWishlistsByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Wishlist> wishlists = wishlistRepository.findAll(withOwnerId(user.getIdUser()), pageable);
        List<WishlistResponse> wishlistResponse = wishlists.stream()
                .map(wishlistMapper::toWishlistResponse)
                .toList();
        return new PageResponse<>(
                wishlistResponse,
                wishlists.getNumber(),
                wishlists.getSize(),
                wishlists.getTotalElements(),
                wishlists.getTotalPages(),
                wishlists.isFirst(),
                wishlists.isLast()
        );
    }
}
