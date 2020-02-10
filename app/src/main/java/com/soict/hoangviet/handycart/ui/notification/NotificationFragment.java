package com.soict.hoangviet.handycart.ui.notification;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.NotificationAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.custom.NotificationItemDecoration;
import com.soict.hoangviet.handycart.databinding.FragmentNotificationBinding;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class NotificationFragment extends BaseFragment<FragmentNotificationBinding> {
    private NotificationViewModel mViewModel;
    private NotificationItemDecoration itemDecoration;
    private NotificationAdapter notificationAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        initViewModel();
        initNotificationItemDecoration();
        initNotificationAdaptper();
    }

    private void initNotificationItemDecoration() {
        itemDecoration = new NotificationItemDecoration(getResources().getDimension(R.dimen.content_padding_4_dp));
    }

    private void initNotificationAdaptper() {
        notificationAdapter = new NotificationAdapter(getContext(), false);
        binding.rcvNotification.setAdapter(notificationAdapter);
        binding.rcvNotification.setOnRefreshListener(() -> {
            getNotification(true);
        });
        binding.rcvNotification.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        binding.rcvNotification.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvNotification.addItemDecoration(itemDecoration);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(NotificationViewModel.class);
        binding.setNotificationViewModel(mViewModel);
    }

    @Override
    public void initData() {
        getNotification(false);
    }

    private void getNotification(boolean isRefresh) {
        if (mViewModel.getmSharePreference().isLogin()) {
            mViewModel.setListNotificationWithAuth(isRefresh);
        } else {
            mViewModel.setListNotificationNoAuth(isRefresh);
        }
    }

    @Override
    public void initListener() {
        mViewModel.getNotifications().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        if (canLoadmore) binding.rcvNotification.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvNotification.refresh(data);
        } else {
            binding.rcvNotification.addItem(data);
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCategoryChangeEvent(AuthorizationEvent authorizationEvent) {
        getNotification(true);
    }
}
