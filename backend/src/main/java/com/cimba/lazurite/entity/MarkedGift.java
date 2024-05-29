//package com.cimba.lazurite.entity;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "MarkedGift")
//public class MarkedGift {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_mark")
//    private Integer idMark;
//    @Column(name = "id_user")
//    private Integer idUser;
//    @Column(name = "id_gift")
//    private Integer idGift;
//    @Column(name = "is_gift_selected")
//    private Boolean isGiftSelected;
//    @Column(name = "selection_date")
//    private Date selectionDate;
//
//    public Integer getIdMark() {
//        return idMark;
//    }
//
//    public void setIdMark(Integer idMark) {
//        this.idMark = idMark;
//    }
//
//    public Integer getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(Integer idUser) {
//        this.idUser = idUser;
//    }
//
//    public Integer getIdGift() {
//        return idGift;
//    }
//
//    public void setIdGift(Integer idGift) {
//        this.idGift = idGift;
//    }
//
//    public Boolean getGiftSelected() {
//        return isGiftSelected;
//    }
//
//    public void setGiftSelected(Boolean giftSelected) {
//        isGiftSelected = giftSelected;
//    }
//
//    public Date getSelectionDate() {
//        return selectionDate;
//    }
//
//    public void setSelectionDate(Date selectionDate) {
//        this.selectionDate = selectionDate;
//    }
//}
