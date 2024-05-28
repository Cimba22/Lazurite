package com.cimba.lazurite.gift;

import com.cimba.lazurite.entity.common.PageResponse;
import com.cimba.lazurite.wishlist.WishlistResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gifts")
@RequiredArgsConstructor
@Tag(name = "Gift")
public class GiftController {
    private final GiftService service;

    @PostMapping
    public ResponseEntity<Long> saveGift(
            @Valid @RequestBody GiftRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("{gift-id}")
    public ResponseEntity<GiftResponse> findGiftById(@PathVariable("gift-id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<GiftResponse>> findAllGifts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllGifts(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<GiftResponse>> findAllGiftsByOwner(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllGiftsByOwner(page, size, connectedUser));
    }
}
