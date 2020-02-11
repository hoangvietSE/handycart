package com.soict.hoangviet.handycart.entity.response;

import com.google.gson.annotations.SerializedName;

public class NotificationReadResponse{

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private Object type;

	@SerializedName("notify_id")
	private int notifyId;

	@SerializedName("status")
	private int status;

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(Object type){
		this.type = type;
	}

	public Object getType(){
		return type;
	}

	public void setNotifyId(int notifyId){
		this.notifyId = notifyId;
	}

	public int getNotifyId(){
		return notifyId;
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
			"NotificationReadResponse{" + 
			"device_id = '" + deviceId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",notify_id = '" + notifyId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}