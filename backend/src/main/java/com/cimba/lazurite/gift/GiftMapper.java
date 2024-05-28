package com.cimba.lazurite.gift;

import com.cimba.lazurite.entity.Gift;

public class GiftMapper {
    public Gift toGift(GiftRequest request) {
        return Gift.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                //.image()
                .archived(false)
                .completed(false)
                .build();
    }


    public GiftResponse toGiftResponse(Gift gift) {
        return GiftResponse.builder()
                .id(gift.getId())
                .name(gift.getName())
                .description(gift.getDescription())
                .owner(gift.getOwner().getLogin())
                //.image
                .build();
    }
}
