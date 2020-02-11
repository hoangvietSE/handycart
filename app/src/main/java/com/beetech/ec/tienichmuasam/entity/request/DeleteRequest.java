package com.beetech.ec.tienichmuasam.entity.request;

import com.google.gson.annotations.SerializedName;
import com.beetech.ec.tienichmuasam.utils.Define;

public class DeleteRequest {
    @SerializedName("_method")
    private String method = Define.Api.Method.DELETE;
}
