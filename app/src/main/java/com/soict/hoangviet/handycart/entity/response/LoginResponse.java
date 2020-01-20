package com.soict.hoangviet.handycart.entity.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("address_home")
	private Object addressHome;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("gender")
	private Object gender;

	@SerializedName("phone")
	private String phone;

	@SerializedName("user_name")
	private Object userName;

	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("email")
	private String email;

	@SerializedName("address_work")
	private Object addressWork;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setAddressHome(Object addressHome){
		this.addressHome = addressHome;
	}

	public Object getAddressHome(){
		return addressHome;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setGender(Object gender){
		this.gender = gender;
	}

	public Object getGender(){
		return gender;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setUserName(Object userName){
		this.userName = userName;
	}

	public Object getUserName(){
		return userName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAddressWork(Object addressWork){
		this.addressWork = addressWork;
	}

	public Object getAddressWork(){
		return addressWork;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"access_token = '" + accessToken + '\'' + 
			",address_home = '" + addressHome + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",gender = '" + gender + '\'' + 
			",phone = '" + phone + '\'' + 
			",user_name = '" + userName + '\'' + 
			",id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",email = '" + email + '\'' + 
			",address_work = '" + addressWork + '\'' + 
			"}";
		}
}