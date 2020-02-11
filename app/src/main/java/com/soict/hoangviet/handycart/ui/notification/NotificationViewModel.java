package com.soict.hoangviet.handycart.ui.notification;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.soict.hoangviet.handycart.base.BaseViewModel;
import com.soict.hoangviet.handycart.base.ListLoadmoreReponse;
import com.soict.hoangviet.handycart.base.ObjectResponse;
import com.soict.hoangviet.handycart.data.network.repository.Repository;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.entity.response.NotificationReadResponse;
import com.soict.hoangviet.handycart.entity.response.NotificationResponse;
import com.soict.hoangviet.handycart.utils.Define;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class NotificationViewModel extends BaseViewModel {
    private Repository repository;
    private int pageIndexNotification = 1;
    private MutableLiveData<ListLoadmoreReponse<NotificationResponse>> notifications;
    private MutableLiveData<ObjectResponse<NotificationReadResponse>> notificationsUpdate;
    private MutableLiveData<Boolean> notificationBadge;

    @Inject
    public NotificationViewModel(Context context, Repository repository, ISharePreference mSharePreference) {
        super(context, mSharePreference);
        this.repository = repository;
    }

    public MutableLiveData<ListLoadmoreReponse<NotificationResponse>> getNotifications() {
        if (notifications == null) notifications = new MutableLiveData<>();
        return notifications;
    }

    public MutableLiveData<Boolean> getNotificationBadge() {
        if (notificationBadge == null) notificationBadge = new MutableLiveData<>();
        return notificationBadge;
    }

    public MutableLiveData<ObjectResponse<NotificationReadResponse>> getNotificationsUpdate() {
        if (notificationsUpdate == null) notificationsUpdate = new MutableLiveData<>();
        return notificationsUpdate;
    }

    public void setListNotificationWithAuth(boolean isRefresh) {
        if (isRefresh) pageIndexNotification = 1;
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.DEVICE_ID, getmSharePreference().getDeviceTokenId());
        data.put(Define.Api.Query.PAGE, pageIndexNotification);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getNotificationWithAuth(getmSharePreference().getAccessToken(), data)
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                response -> {
                                    pageIndexNotification++;
                                    getNotificationBadge().setValue(response.getCountUserNotifications() != 0);
                                    getNotifications().setValue(new ListLoadmoreReponse<NotificationResponse>().success(
                                            response.getData(),
                                            isRefresh,
                                            pageIndexNotification <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getNotifications().setValue(new ListLoadmoreReponse<NotificationResponse>().error(throwable));
                                })
        );
    }

    public void setListNotificationNoAuth(boolean isRefresh) {
        if (isRefresh) pageIndexNotification = 1;
        HashMap<String, Object> data = new HashMap<>();
        data.put(Define.Api.Query.DEVICE_ID, getmSharePreference().getDeviceTokenId());
        data.put(Define.Api.Query.PAGE, pageIndexNotification);
        data.put(Define.Api.Query.LIMIT, Define.Api.BaseResponse.DEFAULT_LIMIT);
        mCompositeDisposable.add(
                repository.getNotificationNoAuth(data)
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                response -> {
                                    pageIndexNotification++;
                                    getNotifications().setValue(new ListLoadmoreReponse<NotificationResponse>().success(
                                            response.getData(),
                                            isRefresh,
                                            pageIndexNotification <= response.getTotalPage()
                                    ));
                                },
                                throwable -> {
                                    getNotifications().setValue(new ListLoadmoreReponse<NotificationResponse>().error(throwable));
                                })
        );
    }

    public void updateNotificationWithAuth(NotificationResponse notificationResponse) {
        mCompositeDisposable.add(
                repository.updateNotificationWithAuth(getmSharePreference().getAccessToken(), notificationResponse.getId(), getmSharePreference().getDeviceTokenId())
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                response -> {
                                    getNotificationsUpdate().setValue(new ObjectResponse<NotificationReadResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getNotificationsUpdate().setValue(new ObjectResponse<NotificationReadResponse>().error(throwable));
                                })
        );
    }

    public void updateNotificationNoAuth(NotificationResponse notificationResponse) {
        mCompositeDisposable.add(
                repository.updateNotificationNoAuth(notificationResponse.getId(), getmSharePreference().getDeviceTokenId())
                        .doOnSubscribe(disposable -> {
                        })
                        .subscribe(
                                response -> {
                                    getNotificationsUpdate().setValue(new ObjectResponse<NotificationReadResponse>().success(response.getData()));
                                },
                                throwable -> {
                                    getNotifications().setValue(new ListLoadmoreReponse<NotificationResponse>().error(throwable));
                                })
        );
    }


}
