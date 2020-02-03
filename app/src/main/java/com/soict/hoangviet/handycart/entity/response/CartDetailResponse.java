package com.soict.hoangviet.handycart.entity.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartDetailResponse{

	@SerializedName("cart_id")
	private int cartId;

	@SerializedName("totalQuantity")
	private int totalQuantity;

	@SerializedName("totalPrice")
	private int totalPrice;

	@SerializedName("productList")
	private List<ProductListItem> productList;

	public void setCartId(int cartId){
		this.cartId = cartId;
	}

	public int getCartId(){
		return cartId;
	}

	public void setTotalQuantity(int totalQuantity){
		this.totalQuantity = totalQuantity;
	}

	public int getTotalQuantity(){
		return totalQuantity;
	}

	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice(){
		return totalPrice;
	}

	public void setProductList(List<ProductListItem> productList){
		this.productList = productList;
	}

	public List<ProductListItem> getProductList(){
		return productList;
	}

	@Override
 	public String toString(){
		return 
			"CartDetailResponse{" + 
			"cart_id = '" + cartId + '\'' + 
			",totalQuantity = '" + totalQuantity + '\'' + 
			",totalPrice = '" + totalPrice + '\'' + 
			",productList = '" + productList + '\'' + 
			"}";
		}
}