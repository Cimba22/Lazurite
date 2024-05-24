package com.cimba.lazurite.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WishlistRequest(
        Long id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String name,
        String description,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String image

) {
}
