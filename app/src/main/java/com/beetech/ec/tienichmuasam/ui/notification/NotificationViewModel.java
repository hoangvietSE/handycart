package com.beetech.ec.tienichmuasam.ui.notification;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.response.NotificationReadResponse;
import com.beetech.ec.tienichmuasam.entity.response.NotificationResponse;
import com.beetech.ec.tienichmuasam.utils.Define;

import java.util.HashMap;

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
