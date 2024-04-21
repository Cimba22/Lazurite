package com.cimba.lazurite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ListRole")
public class ListRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_list")
    private Integer idRoleList;
    @Column(name = "role_list_name")
    private String roleListName;

    public Integer getIdRoleList() {
        return idRoleList;
    }

    public void setIdRoleList(Integer idRoleList) {
        this.idRoleList = idRoleList;
    }

    public String getRoleListName() {
        return roleListName;
    }

    public void setRoleListName(String roleListName) {
        this.roleListName = roleListName;
    }
}
