package com.cimba.lazurite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "GiftType")
public class GiftType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Integer idType;
    @Column(name = "type_name")
    private String typeName;


    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
