package com.cimba.lazurite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RoleListAssignment")
public class RoleListAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment")
    private Integer idAssignment;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "id_list")
    private Integer idList;
    @Column(name = "id_role_list")
    private Integer idRoleList;

    public Integer getIdAssignment() {
        return idAssignment;
    }

    public void setIdAssignment(Integer idAssignment) {
        this.idAssignment = idAssignment;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public Integer getIdRoleList() {
        return idRoleList;
    }

    public void setIdRoleList(Integer idRoleList) {
        this.idRoleList = idRoleList;
    }
}
