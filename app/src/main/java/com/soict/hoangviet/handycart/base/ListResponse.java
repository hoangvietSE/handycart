package com.soict.hoangviet.handycart.base;

import androidx.annotation.NonNull;


import com.soict.hoangviet.handycart.utils.Define;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class ListResponse<T> extends BaseResponse {
    private int type;

    @SerializedName("total_page")
    private int totalPage;

    @SerializedName("total_item")
    private int totalItem;

    @SerializedName("count_user_notifications")
    private int countUserNotifications;

    @Nullable
    private List<T> data;

    @Nullable
    private Throwable error;

    public ListResponse() {
    }

    public ListResponse(int type, List<T> data, Throwable error) {
        this.type = type;
        this.data = data;
        this.error = error;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getType() {
        return type;
    }

    public List<T> getData() {
        return data;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public Throwable getError() {
        return error;
    }

    public int getCountUserNotifications() {
        return countUserNotifications;
    }

    public void setCountUserNotifications(int countUserNotifications) {
        this.countUserNotifications = countUserNotifications;
    }

    public ListResponse<T> loading() {
        return new ListResponse<>(Define.ResponseStatus.LOADING, null, null);
    }

    public ListResponse<T> success(@NonNull List<T> data) {
        return new ListResponse<>(Define.ResponseStatus.SUCCESS, data, null);
    }

    public ListResponse<T> error(@NonNull Throwable throwable) {
        return new ListResponse<>(Define.ResponseStatus.ERROR, null, throwable);
    }
}
