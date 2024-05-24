package com.cimba.lazurite.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistResponse {
    private Long id;
    private String name;
    private String description;
    private byte[] image;
    private String owner;
}
