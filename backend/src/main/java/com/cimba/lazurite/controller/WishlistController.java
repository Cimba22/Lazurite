package com.cimba.lazurite.controller;

import com.cimba.lazurite.entity.dto.WishlistRequest;
import com.cimba.lazurite.entity.dto.WishlistResponse;
import com.cimba.lazurite.service.PageResponse;
import com.cimba.lazurite.service.WishlistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wishlists")
@RequiredArgsConstructor
@Tag(name = "Wishlist")
public class WishlistController {
    private final WishlistService service;

    @PostMapping
    public ResponseEntity<Long> saveWishlist(
            @Valid @RequestBody WishlistRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("{wishlist-id}")
    public ResponseEntity<WishlistResponse> findWishlistById(@PathVariable("wishlist-id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<WishlistResponse>> findAllWishlists(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllWishlists(page, size, connectedUser));
    }
}
