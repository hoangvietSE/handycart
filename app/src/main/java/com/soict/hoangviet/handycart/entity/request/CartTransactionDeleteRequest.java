package com.soict.hoangviet.handycart.entity.request;

import com.google.gson.annotations.SerializedName;

public class CartTransactionDeleteRequest {
    @SerializedName("cart_id")
    private int cartId;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("product_id")
    private int productId;

    public CartTransactionDeleteRequest(int cartId, String deviceId, int productId) {
        this.cartId = cartId;
        this.deviceId = deviceId;
        this.productId = productId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return
                "CartTransactionRequest{" +
                        "cart_id = '" + cartId + '\'' +
                        ",device_id = '" + deviceId + '\'' +
                        ",product_id = '" + productId + '\'' +
                        "}";
    }
}
