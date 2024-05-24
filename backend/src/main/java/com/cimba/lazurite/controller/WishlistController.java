package com.cimba.lazurite.controller;

import com.cimba.lazurite.entity.dto.WishlistRequest;
import com.cimba.lazurite.service.WishlistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
