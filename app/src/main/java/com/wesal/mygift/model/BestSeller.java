package com.wesal.mygift.model;

import java.io.Serializable;

public class BestSeller implements Serializable {
    private String bsImgUrl;
    private String bsTitle;
    private String bsPrice;
    private String bsCategory;
    private String bsDescription;


    public String getBsImgUrl() {
        return bsImgUrl;
    }

    public void setBsImgUrl(String bsImgUrl) {
        this.bsImgUrl = bsImgUrl;
    }

    public String getBsTitle() {
        return bsTitle;
    }

    public void setBsTitle(String bsTitle) {
        this.bsTitle = bsTitle;
    }

    public String getBsPrice() {
        return bsPrice;
    }

    public void setBsPrice(String bsPrice) {
        this.bsPrice = bsPrice;
    }

    public String getBsCategory() {
        return bsCategory;
    }

    public void setBsCategory(String bsCategory) {
        this.bsCategory = bsCategory;
    }


    public String getBsDescription() {
        return bsDescription;
    }

    public void setBsDescription(String bsDescription) {
        this.bsDescription = bsDescription;
    }
}
