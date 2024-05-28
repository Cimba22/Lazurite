package com.cimba.lazurite.gift;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftResponse {
    private Long id;
    private String name;
    private String description;
    private byte[] image;
    private String owner;
}
