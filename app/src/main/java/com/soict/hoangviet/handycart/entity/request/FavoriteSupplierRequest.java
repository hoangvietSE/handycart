package com.soict.hoangviet.handycart.entity.request;

import com.google.gson.annotations.SerializedName;

public class FavoriteSupplierRequest {
    @SerializedName("supplier_id")
    private int supplierId;

    public FavoriteSupplierRequest(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
