package com.wesal.mygift.model;

import java.io.Serializable;

public class NewProduct implements Serializable {
    private String npImgUrl;
    private String npTitle;
    private String npPrice;
    private String npCategory;
    private String npAvailability;
    private String npDescription;

    public String getNpImgUrl() {
        return npImgUrl;
    }

    public void setNpImgUrl(String npImgUrl) {
        this.npImgUrl = npImgUrl;
    }

    public String getNpTitle() {
        return npTitle;
    }

    public void setNpTitle(String npTitle) {
        this.npTitle = npTitle;
    }

    public String getNpPrice() {
        return npPrice;
    }

    public void setNpPrice(String npPrice) {
        this.npPrice = npPrice;
    }

    public String getNpCategory() {
        return npCategory;
    }

    public void setNpCategory(String npCategory) {
        this.npCategory = npCategory;
    }

    public String getNpAvailability() {
        return npAvailability;
    }

    public void setNpAvailability(String npAvailability) {
        this.npAvailability = npAvailability;
    }

    public String getNpDescription() {
        return npDescription;
    }

    public void setNpDescription(String npDescription) {
        this.npDescription = npDescription;
    }
}
