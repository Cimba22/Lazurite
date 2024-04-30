package com.cimba.lazurite.entity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    @Column(name = "id_role")
    private Integer idRole;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "registration_date")
    private Date registrationDate;

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getRegistrtionDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrtionDate) {
        this.registrationDate = registrtionDate;
    }
}
