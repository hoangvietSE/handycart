package com.soict.hoangviet.handycart.data.network.model;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.List;

public class ApiArrayResponse<T> {

    @SerializedName(Define.Api.BaseResponse.STATUS)
    private Integer stauts;

    @SerializedName(Define.Api.BaseResponse.MESSAGE)
    private String msg;

    @SerializedName(Define.Api.BaseResponse.DATA)
    private List<T> data;

    @SerializedName(Define.Api.BaseResponse.PAGE)
    private Integer page;

    @SerializedName(Define.Api.BaseResponse.CURRENT_PAGE)
    private Integer currentPage;

    @SerializedName(Define.Api.BaseResponse.TOTAL_PAGE)
    private Integer totalPage;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
