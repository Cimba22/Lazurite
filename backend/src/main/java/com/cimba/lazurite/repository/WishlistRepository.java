package com.cimba.lazurite.repository;

import com.cimba.lazurite.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>, JpaSpecificationExecutor<Wishlist> {
    @Query("""
            SELECT wishlist
            from Wishlist wishlist
            WHERE wishlist.owner.idUser != :idUser
""")
    Page<Wishlist> findAllDisplayableWishlists(Pageable pageable, Long idUser);
}
