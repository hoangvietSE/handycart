package com.soict.hoangviet.handycart.entity.response;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.module.GlideApp;

public class DataItem {

    @SerializedName("no")
    private int no;

    @SerializedName("image")
    private String image;

    @SerializedName("thumbnail")
    private Object thumbnail;

    @SerializedName("id")
    private int id;

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Object getThumbnail() {
        return thumbnail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "no = '" + no + '\'' +
                        ",image = '" + image + '\'' +
                        ",thumbnail = '" + thumbnail + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}