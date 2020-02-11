package com.beetech.ec.tienichmuasam.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Supplier implements Parcelable {

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
    private int phone;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private int status;

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

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
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

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "Supplier{" +
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
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.role);
        dest.writeString(this.address);
        dest.writeString(this.banner);
        dest.writeString(this.createdAt);
        dest.writeString(this.description);
        dest.writeString(this.avatar);
        dest.writeString(this.updatedAt);
        dest.writeString(this.moreInformation);
        dest.writeInt(this.phone);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.email);
        dest.writeInt(this.status);
    }

    public Supplier() {
    }

    protected Supplier(Parcel in) {
        this.policyService = in.readParcelable(Object.class.getClassLoader());
        this.role = in.readInt();
        this.address = in.readString();
        this.banner = in.readString();
        this.createdAt = in.readString();
        this.description = in.readString();
        this.avatar = in.readString();
        this.content = in.readParcelable(Object.class.getClassLoader());
        this.updatedAt = in.readString();
        this.moreInformation = in.readString();
        this.phone = in.readInt();
        this.name = in.readString();
        this.id = in.readInt();
        this.email = in.readString();
        this.status = in.readInt();
    }

    public static final Creator<Supplier> CREATOR = new Creator<Supplier>() {
        @Override
        public Supplier createFromParcel(Parcel source) {
            return new Supplier(source);
        }

        @Override
        public Supplier[] newArray(int size) {
            return new Supplier[size];
        }
    };
}