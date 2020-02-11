package com.beetech.ec.tienichmuasam.entity.request;

import com.google.gson.annotations.SerializedName;

public class FavoriteProductRequest {
    @SerializedName("product_id")
    private int productId;

    public FavoriteProductRequest(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
