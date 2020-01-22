package com.soict.hoangviet.handycart.entity.request;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.utils.Define;

public class DeleteRequest {
    @SerializedName("_method")
    private String method = Define.Api.Method.DELETE;
}
