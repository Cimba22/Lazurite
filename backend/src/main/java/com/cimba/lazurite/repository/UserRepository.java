package com.cimba.lazurite.repository;

import com.cimba.lazurite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);


    @Query("SELECT u FROM User u JOIN u.wishlists w WHERE w.id = :wishlistId")
    Page<User> findMembersByWishlistId(Long wishlistId, Pageable pageable);
}
