package com.soict.hoangviet.handycart.entity.request;

import com.google.gson.annotations.SerializedName;

public class FavoriteRequest {
    @SerializedName("product_id")
    private int productId;

    public FavoriteRequest(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
