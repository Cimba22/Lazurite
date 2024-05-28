package com.cimba.lazurite.repository;

import com.cimba.lazurite.entity.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long>, JpaSpecificationExecutor<Gift> {
    @Query("""
        SELECT gift
        FROM Gift gift
        WHERE gift.archived = false 
        AND gift.completed = false
        AND gift.owner.idUser != :idUser
""")
    Page<Gift> findAllDisplayableGifts(Pageable pageable, Long idUser);
}
