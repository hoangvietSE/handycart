package com.soict.hoangviet.handycart.entity;

import com.google.gson.annotations.SerializedName;

public class UrlDiscountItem{

	@SerializedName("image")
	private String image;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("id")
	private int id;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"UrlDiscountItem{" + 
			"image = '" + image + '\'' + 
			",thumbnail = '" + thumbnail + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}