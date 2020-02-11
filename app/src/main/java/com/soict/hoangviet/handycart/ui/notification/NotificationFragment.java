package com.soict.hoangviet.handycart.ui.notification;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.NotificationAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.custom.NotificationItemDecoration;
import com.soict.hoangviet.handycart.databinding.FragmentNotificationBinding;
import com.soict.hoangviet.handycart.entity.response.NotificationResponse;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;
import com.soict.hoangviet.handycart.ui.main.MainViewModel;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.DialogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class NotificationFragment extends BaseFragment<FragmentNotificationBinding> {
    private NotificationViewModel mViewModel;
    private MainViewModel mainViewModel;
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
            NotificationResponse notificationResponse = notificationAdapter.getItem(position, NotificationResponse.class);
            notificationResponse.setStatus(Define.Notification.ALREADY_READ);
            notificationAdapter.notifyItemChanged(position);
            DialogUtil.showContentDialog(
                    getContext(),
                    R.layout.layout_dialog_show_notification,
                    true,
                    notificationResponse,
                    new DialogUtil.OnAddDataToDialogListener() {
                        @Override
                        public <T> void onData(View view, T data) {
                            if (data instanceof NotificationResponse) {
                                TextView tvTitle = view.findViewById(R.id.tv_title);
                                TextView tvContent = view.findViewById(R.id.tv_content);
                                tvTitle.setText(Html.fromHtml(((NotificationResponse) data).getTitle()));
                                tvContent.setText(Html.fromHtml(((NotificationResponse) data).getBody()));
                            }
                        }
                    }
            );
        });
        binding.rcvNotification.setOnLoadingMoreListener(() -> {
            getNotification(false);
        });
        binding.rcvNotification.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvNotification.addItemDecoration(itemDecoration);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(NotificationViewModel.class);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
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
        mViewModel.getNotificationBadge().observe(this, isShowBadge -> {
            mainViewModel.getNotificationBadge().setValue(isShowBadge);
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvNotification.enableLoadmore(canLoadmore);
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
