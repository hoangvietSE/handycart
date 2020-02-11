package com.beetech.ec.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

public class CartAmountResponse {
    @SerializedName("amount")
    int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
