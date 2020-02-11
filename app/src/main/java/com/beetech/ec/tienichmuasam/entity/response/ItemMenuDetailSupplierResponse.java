package com.beetech.ec.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

public class ItemMenuDetailSupplierResponse{

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

	@SerializedName("content")
	private String content;

	@SerializedName("regular_price")
	private int regularPrice;

	@SerializedName("seller_price")
	private Object sellerPrice;

	@SerializedName("sale")
	private Object sale;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("sku")
	private String sku;

	@SerializedName("stock")
	private int stock;

	@SerializedName("flag_expiry")
	private int flagExpiry;

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

	public void setSellerPrice(Object sellerPrice){
		this.sellerPrice = sellerPrice;
	}

	public Object getSellerPrice(){
		return sellerPrice;
	}

	public void setSale(Object sale){
		this.sale = sale;
	}

	public Object getSale(){
		return sale;
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

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return sku;
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
			"ItemMenuDetailSupplierResponse{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",expiry_date = '" + expiryDate + '\'' + 
			",weight = '" + weight + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",content = '" + content + '\'' + 
			",regular_price = '" + regularPrice + '\'' + 
			",seller_price = '" + sellerPrice + '\'' + 
			",sale = '" + sale + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",sku = '" + sku + '\'' + 
			",stock = '" + stock + '\'' + 
			",flag_expiry = '" + flagExpiry + '\'' + 
			",flag_stock = '" + flagStock + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}