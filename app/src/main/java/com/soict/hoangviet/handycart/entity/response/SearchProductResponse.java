package com.soict.hoangviet.handycart.entity.response;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.module.GlideApp;

public class SearchProductResponse{

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("expiry_date")
	private String expiryDate;

	@SerializedName("weight")
	private int weight;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private int discount;

	@SerializedName("content")
	private String content;

	@SerializedName("regular_price")
	private int regularPrice;

	@SerializedName("seller_price")
	private int sellerPrice;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("stock")
	private int stock;

	@SerializedName("flag_expiry")
	private int flagExpiry;

	@SerializedName("slug")
	private Object slug;

	@SerializedName("flag_stock")
	private int flagStock;

	@SerializedName("status")
	private int status;

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setExpiryDate(String expiryDate){
		this.expiryDate = expiryDate;
	}

	public String getExpiryDate(){
		return expiryDate;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getWeight(){
		return weight;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDiscount(int discount){
		this.discount = discount;
	}

	public int getDiscount(){
		return discount;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setRegularPrice(int regularPrice){
		this.regularPrice = regularPrice;
	}

	public int getRegularPrice(){
		return regularPrice;
	}

	public void setSellerPrice(int sellerPrice){
		this.sellerPrice = sellerPrice;
	}

	public int getSellerPrice(){
		return sellerPrice;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}

	public void setFlagExpiry(int flagExpiry){
		this.flagExpiry = flagExpiry;
	}

	public int getFlagExpiry(){
		return flagExpiry;
	}

	public void setSlug(Object slug){
		this.slug = slug;
	}

	public Object getSlug(){
		return slug;
	}

	public void setFlagStock(int flagStock){
		this.flagStock = flagStock;
	}

	public int getFlagStock(){
		return flagStock;
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
			"SearchProductResponse{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",expiry_date = '" + expiryDate + '\'' + 
			",weight = '" + weight + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",discount = '" + discount + '\'' + 
			",content = '" + content + '\'' + 
			",regular_price = '" + regularPrice + '\'' + 
			",seller_price = '" + sellerPrice + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' +
			",stock = '" + stock + '\'' + 
			",flag_expiry = '" + flagExpiry + '\'' + 
			",slug = '" + slug + '\'' + 
			",flag_stock = '" + flagStock + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@BindingAdapter({"imageUrl", "error"})
	public static void loadImage(ImageView view, String url, Drawable error) {
		GlideApp.with(view.getContext())
				.load(url)
				.placeholder(error)
				.error(error)
				.into(view);
	}

}