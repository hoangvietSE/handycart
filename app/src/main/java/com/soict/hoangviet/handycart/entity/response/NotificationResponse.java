package com.soict.hoangviet.handycart.entity.response;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.utils.Define;

public class NotificationResponse {

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("user_id")
    private Object userId;

    @SerializedName("id")
    private int id;

    @SerializedName("created_date")
    private String createdDate;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("type")
    private Object type;

    @SerializedName("status")
    private int status;

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getType() {
        return type;
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
                "NotificationResponse{" +
                        "device_id = '" + deviceId + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",id = '" + id + '\'' +
                        ",created_date = '" + createdDate + '\'' +
                        ",title = '" + title + '\'' +
                        ",body = '" + body + '\'' +
                        ",type = '" + type + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @BindingAdapter({"backgroundNotification"})
    public static void loadImage(ConstraintLayout view, int status) {
        if (status == Define.Notification.UN_READ) {
            view.setBackgroundResource(R.drawable.bg_noti_unread);
        } else if (status == Define.Notification.ALREADY_READ) {
            view.setBackgroundResource(R.drawable.bg_noti_read);
        }
    }

    @BindingAdapter({"ringNofication"})
    public static void loadImage(ImageView view, int status) {
        if (status == Define.Notification.UN_READ) {
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.color_green), android.graphics.PorterDuff.Mode.MULTIPLY);
        } else if (status == Define.Notification.ALREADY_READ) {
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.color_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @BindingAdapter({"borderNotification"})
    public static void loadImage(View view, int status) {
        if (status == Define.Notification.UN_READ) {
            view.setBackgroundResource(R.drawable.bg_notification_unread);
        } else if (status == Define.Notification.ALREADY_READ) {
            view.setBackgroundResource(R.drawable.bg_notification_border);
        }
    }

}