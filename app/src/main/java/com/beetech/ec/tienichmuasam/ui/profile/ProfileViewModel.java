package com.beetech.ec.tienichmuasam.ui.profile;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.beetech.ec.tienichmuasam.base.BaseViewModel;
import com.beetech.ec.tienichmuasam.base.ObjectResponse;
import com.beetech.ec.tienichmuasam.data.network.repository.Repository;
import com.beetech.ec.tienichmuasam.data.sharepreference.ISharePreference;
import com.beetech.ec.tienichmuasam.entity.request.DeleteRequest;
import com.beetech.ec.tienichmuasam.eventbus.AuthorizationEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class ProfileViewModel extends BaseViewModel {

    private Repository repository;
    private MutableLiveData<ObjectResponse> responseLogout;
    private MutableLiveData<Boolean> isVisibleLiveData;

    @Inject
    public ProfileViewModel(Context context, ISharePreference mSharePreference, Repository repository) {
        super(context, mSharePreference);
        this.repository = repository;
        checkLogin();
    }

    public MutableLiveData<ObjectResponse> getResponseLogout() {
        if (responseLogout == null) responseLogout = new MutableLiveData<>();
        return responseLogout;
    }

    public void setResponseLogout(MutableLiveData<ObjectResponse> responseLogout) {
        this.responseLogout = responseLogout;
    }

    public MutableLiveData<Boolean> getIsVisibleLiveData() {
        if (isVisibleLiveData == null) isVisibleLiveData = new MutableLiveData<>();
        return isVisibleLiveData;
    }

    public void setIsVisibleLiveData(MutableLiveData<Boolean> isVisibleLiveData) {
        this.isVisibleLiveData = isVisibleLiveData;
    }

    private void checkLogin() {
        getIsVisibleLiveData().setValue(mSharePreference.isLogin());
    }

    public void logOut() {
        DeleteRequest deleteRequest = new DeleteRequest();
        mCompositeDisposable.add(
                repository.logOut(
                        mSharePreference.getAccessToken(),
                        mSharePreference.getUserId(),
                        deleteRequest
                )
                        .doOnSubscribe(disposable -> {
                            getResponseLogout().setValue(new ObjectResponse<>().loading());
                        })
                        .doFinally(() -> {
                        })
                        .subscribe(
                                () -> {
                                    mSharePreference.clearAllPreference();
                                    EventBus.getDefault().postSticky(new AuthorizationEvent(false));
                                    getResponseLogout().setValue(new ObjectResponse<>().success(null));
                                    getIsVisibleLiveData().setValue(false);
                                }
                                ,throwable -> {
                                    getResponseLogout().setValue(new ObjectResponse<>().error(throwable));
                                }
                        )
        );
    }

    @BindingAdapter({"android:visible"})
    public static void visibility(RelativeLayout view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

}
