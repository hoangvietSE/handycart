package com.beetech.ec.tienichmuasam.base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("msg")
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


}
