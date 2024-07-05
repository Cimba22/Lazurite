package com.cimba.lazurite.wishlist;

import com.cimba.lazurite.entity.common.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PutMapping("{wishlist-id}")
    public ResponseEntity<WishlistResponse> updateWishlist(
            @PathVariable("wishlist-id") Long id,
            @Valid
            @RequestBody WishlistRequest request,
            Authentication connectedUser){
            return ResponseEntity.ok(service.updateWishlist(id, request, connectedUser));
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

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<WishlistResponse>> findAllWishlistsByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllWishlistsByOwner(page, size, connectedUser));
    }

    @PostMapping("{wishlist-id}/members")
    public ResponseEntity<?> addMemberToWishlist(
            @PathVariable("wishlist-id") Long wishlistId,
            @RequestParam("user-id") Long userId,
            Authentication connectedUser
    ) {
        service.addMemberToWishlist(wishlistId, userId, connectedUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{wishlist-id}/members")
    public ResponseEntity<?> removeMemberFromWishlist(
            @PathVariable("wishlist-id") Long wishlistId,
            @RequestParam("user-id") Long userId,
            Authentication connectedUser
    ) {
        service.removeMemberFromWishlist(wishlistId, userId, connectedUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{wishlist-id}/members")
    public ResponseEntity<PageResponse<MemberResponse>> findWishlistMembers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable("wishlist-id") Long wishlistId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findWishlistMembers(page, size, wishlistId, connectedUser));
    }



    @PostMapping(value = "/cover/{wishlist-id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadWishlistCoverPicture(
            @PathVariable("wishlist-id") Long wishlistId,
            @RequestPart("file") MultipartFile file,
            @Parameter()
            Authentication connectedUser
    ){
        service.uploadWishlistCoverPicture(file, connectedUser, wishlistId);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{wishlist-id}")
    public ResponseEntity<?> deleteWishlist(
            @PathVariable("wishlist-id") Long id,
            Authentication connectedUser
    ) {
        service.deleteWishlist(id, connectedUser);
        return ResponseEntity.noContent().build();
    }
}
