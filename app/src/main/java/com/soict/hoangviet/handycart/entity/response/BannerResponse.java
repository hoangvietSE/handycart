package com.soict.hoangviet.handycart.entity.response;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.module.GlideApp;

public class BannerResponse{

	@SerializedName("url_discount")
	private List<UrlDiscountItem> urlDiscount;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("status")
	private int status;

	public void setUrlDiscount(List<UrlDiscountItem> urlDiscount){
		this.urlDiscount = urlDiscount;
	}

	public List<UrlDiscountItem> getUrlDiscount(){
		return urlDiscount;
	}

	public String getImageUrl(){
		return urlDiscount.get(0).getImage();
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"BannerResponse{" + 
			"url_discount = '" + urlDiscount + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@BindingAdapter({"android:src"})
	public static void setImageViewResource(ImageView imageView, String url) {
		GlideApp.with(imageView.getContext())
				.load(url)
				.into(imageView);
	}
}