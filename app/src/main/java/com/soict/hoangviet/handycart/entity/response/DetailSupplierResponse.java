package com.soict.hoangviet.handycart.entity.response;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;

public class DetailSupplierResponse {

    @SerializedName("policy_service")
    private Object policyService;

    @SerializedName("role")
    private int role;

    @SerializedName("address")
    private String address;

    @SerializedName("banner")
    private String banner;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("description")
    private String description;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("content")
    private Object content;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("more_information")
    private String moreInformation;

    @SerializedName("phone")
    private String phone;

    @SerializedName("qty")
    private int qty;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private String status;

    @SerializedName("flag_fav")
    private int flagFav;

    public void setPolicyService(Object policyService) {
        this.policyService = policyService;
    }

    public Object getPolicyService() {
        return policyService;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBanner() {
        return banner;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setFlagFav(int flagFav) {
        this.flagFav = flagFav;
    }

    public int getFlagFav() {
        return flagFav;
    }

    @Override
    public String toString() {
        return
                "DetailSupplierResponse{" +
                        "policy_service = '" + policyService + '\'' +
                        ",role = '" + role + '\'' +
                        ",address = '" + address + '\'' +
                        ",banner = '" + banner + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",description = '" + description + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",content = '" + content + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",more_information = '" + moreInformation + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",qty = '" + qty + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        ",flag_fav = '" + flagFav + '\'' +
                        "}";
    }

    @BindingAdapter({"imageUrl", "default"})
    public static void loadImageUrl(ImageView view, String url, Drawable drawable) {
        CommonExtensionUtil.loadImageDrawable(
                view,
                url,
                drawable
        );
    }

    @BindingAdapter({"htmlText"})
    public static void showHtmlText(TextView view, String description) {
        if (description != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
            } else {
                view.setText(Html.fromHtml(description));
            }
        }
    }
}