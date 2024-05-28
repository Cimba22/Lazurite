package com.cimba.lazurite.entity;

import com.cimba.lazurite.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gift")
public class Gift extends BaseEntity {

    private String name;
    private String image;
    private String description;
    private Boolean archived;
    private Boolean completed;


    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;




}
