package com.soict.hoangviet.handycart.entity.response;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.R;

public class CategoryResponse {

    @SerializedName("sub_categories")
    private List<SubCategoriesItem> subCategories;

    @SerializedName("thumbnail")
    private Object thumbnail;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("parent_id")
    private int parentId;

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("description")
    private Object description;

    @SerializedName("id")
    private int id;

    @SerializedName("slug")
    private Object slug;

    @SerializedName("content")
    private Object content;

    @SerializedName("status")
    private int status;

    public void setSubCategories(List<SubCategoriesItem> subCategories) {
        this.subCategories = subCategories;
    }

    public List<SubCategoriesItem> getSubCategories() {
        return subCategories;
    }

    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Object getThumbnail() {
        return thumbnail;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSlug(Object slug) {
        this.slug = slug;
    }

    public Object getSlug() {
        return slug;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
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
                "CategoryResponse{" +
                        "sub_categories = '" + subCategories + '\'' +
                        ",thumbnail = '" + thumbnail + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",parent_id = '" + parentId + '\'' +
                        ",name = '" + name + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",description = '" + description + '\'' +
                        ",id = '" + id + '\'' +
                        ",slug = '" + slug + '\'' +
                        ",content = '" + content + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @BindingAdapter({"isExpand"})
    public static void loadImage(ImageView view, Boolean isExpand) {
        if (isExpand) {
            view.setImageDrawable(view.getContext().getDrawable(R.drawable.ic_expand));
        } else {
            view.setImageDrawable(view.getContext().getDrawable(R.drawable.ic_collapse));
        }
    }
}