package com.cimba.lazurite.gift;

import com.cimba.lazurite.entity.Gift;
import org.springframework.data.jpa.domain.Specification;

public class GiftSpecification {
    public static Specification<Gift> withOwnerId(Long ownerId){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
    }
}
