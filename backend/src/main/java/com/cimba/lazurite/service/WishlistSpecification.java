package com.cimba.lazurite.service;

import com.cimba.lazurite.entity.Wishlist;
import org.springframework.data.jpa.domain.Specification;

public class WishlistSpecification {
    public static Specification<Wishlist> withOwnerId(Long ownerId){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
    }
}
