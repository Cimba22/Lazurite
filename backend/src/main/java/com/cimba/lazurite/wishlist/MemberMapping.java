package com.cimba.lazurite.wishlist;

import com.cimba.lazurite.entity.User;
import com.cimba.lazurite.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberMapping {

    public MemberResponse toMemberResponse(User user) {
        return MemberResponse.builder()
                .id(user.getIdUser())
                .name(user.getName())
                .build();
    }
}
