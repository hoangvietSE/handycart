package com.beetech.ec.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

public class CartResponse {
    @SerializedName("total_product")
    int totalProduct;

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
}
