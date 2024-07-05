package com.cimba.lazurite.wishlist;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {
    private Long id;
    private String name;
    private byte[] image;
}
