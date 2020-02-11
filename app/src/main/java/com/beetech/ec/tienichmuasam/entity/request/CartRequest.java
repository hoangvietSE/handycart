package com.beetech.ec.tienichmuasam.entity.request;

import com.google.gson.annotations.SerializedName;

public class CartRequest {
    @SerializedName("device_id")
    String deviceId;
    @SerializedName("product_id")
    int productId;
    @SerializedName("product_quantity")
    int productQuantity;

    public CartRequest(String deviceId, int productId, int productQuantity) {
        this.deviceId = deviceId;
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
