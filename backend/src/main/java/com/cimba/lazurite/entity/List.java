package com.cimba.lazurite.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "List")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_list")
    private Integer idList;
    @Column(name = "list_name")
    private String listName;
    @Column(name = "id_creator")
    private Integer idCreator;
    @Column(name = "creation_date")
    private Date creationDate;

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Integer idCreator) {
        this.idCreator = idCreator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
