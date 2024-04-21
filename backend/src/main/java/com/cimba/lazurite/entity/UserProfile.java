package com.cimba.lazurite.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "UserProfile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Integer idProfile;
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "avatar")
    private Byte avatar;
    @Column(name = "name")
    private String name;
    @Column(name = "email_public")
    private String emailPublic;
    @Column(name = "about")
    private String about;
    @Column(name = "date_birthday")
    private Date dateBirthday;
    @Column(name = "id_social_link")
    private Integer idSocialLink;
    @Column(name = "location")
    private String location;

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Byte getAvatar() {
        return avatar;
    }

    public void setAvatar(Byte avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailPublic() {
        return emailPublic;
    }

    public void setEmailPublic(String emailPublic) {
        this.emailPublic = emailPublic;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public Integer getIdSocialLink() {
        return idSocialLink;
    }

    public void setIdSocialLink(Integer idSocialLink) {
        this.idSocialLink = idSocialLink;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
