package com.cimba.lazurite.gift;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record GiftRequest(
        Long id,
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String name,
        String description,
        Boolean archived,
        Boolean completed


) {
}
