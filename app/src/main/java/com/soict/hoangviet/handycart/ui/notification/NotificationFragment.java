package com.soict.hoangviet.handycart.ui.notification;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentNotificationBinding;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NotificationFragment extends BaseFragment<FragmentNotificationBinding> {
    private NotificationViewModel mViewModel;
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
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(NotificationViewModel.class);
        binding.setNotificationViewModel(mViewModel);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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
        binding.setNotificationViewModel(mViewModel);
    }
}
