package com.cimba.lazurite.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Gift")
public class Gift {

    @Id
    @Column(name = "id_gift")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGift;
    @Column(name = "id_list")
    private Integer idList;
    @Column(name = "gift_name")
    private String giftName;
    @Column(name = "gift_image")
    private String giftImage;
    @Column(name = "gift_description")
    private String giftDescription;
    @Column(name = "id_type")
    private Integer idType;
    @Column(name = "gift_added_by")
    private Integer giftAddedBy;
    @Column(name = "gift_added_date")
    private Date giftAddedDate;
    @Column(name = "is_archived")
    private Boolean isArchived;
    @Column(name = "is_completed")
    private Boolean isCompleted;

    public Integer getIdGift() {
        return idGift;
    }

    public void setIdGift(Integer idGift) {
        this.idGift = idGift;
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftImage() {
        return giftImage;
    }

    public void setGiftImage(String giftImage) {
        this.giftImage = giftImage;
    }

    public String getGiftDescription() {
        return giftDescription;
    }

    public void setGiftDescription(String giftDescription) {
        this.giftDescription = giftDescription;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getGiftAddedBy() {
        return giftAddedBy;
    }

    public void setGiftAddedBy(Integer giftAddedBy) {
        this.giftAddedBy = giftAddedBy;
    }

    public Date getGiftAddedDate() {
        return giftAddedDate;
    }

    public void setGiftAddedDate(Date giftAddedDate) {
        this.giftAddedDate = giftAddedDate;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
