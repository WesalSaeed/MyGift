package com.wesal.mygift.model;

public class CartItem {

    public static final int CART_ITEM = 0;
    public static final int CART_CHECKOUT = 1;



    private String productID;
    private String productImageUrl;
    private String productTitle;
    private String productPrice;
    private int productQuantity;


    //cart total
    private String subTotal;
    private String shipping;
    private String total;

    public CartItem(String productID, String productImageUrl, String productTitle, String productPrice, int productQuantity) {
        this.productID = productID;
        this.productImageUrl = productImageUrl;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public CartItem(String subTotal, String shipping, String total) {

        this.subTotal = subTotal;
        this.shipping = shipping;
        this.total = total;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getproductImageUrl() {
        return productImageUrl;
    }

    public void setproductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
