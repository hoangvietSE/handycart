package com.soict.hoangviet.handycart.ui.master;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.MasterAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentMasterBinding;
import com.soict.hoangviet.handycart.ui.main.MainViewModel;

public class MasterFragment extends BaseFragment<FragmentMasterBinding> {
    private static final int HOME_FRAGMENT = 0;
    private static final int FAVORITE_FRAGMENT = 1;
    private static final int SEARCH_FRAGMENT = 2;
    private static final int NOTIFICATION_FRAGMENT = 3;
    private static final int PROFILE_FRAGMENT = 4;

    private MasterViewModel mViewModel;
    private MainViewModel mainViewModel;
    private MasterAdapter masterAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master;
    }

    @Override
    public void backFromAddFragment() {
        Fragment page = getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.master_container + ":" + binding.masterContainer.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (page != null) {
            switch (binding.masterContainer.getCurrentItem()) {
                case HOME_FRAGMENT:
                    break;
                case FAVORITE_FRAGMENT:
                    break;
                case SEARCH_FRAGMENT:
                    break;
                case NOTIFICATION_FRAGMENT:
                    break;
                case PROFILE_FRAGMENT:
            }
        }
        enableDrawer(true);
    }

    @Override
    public boolean backPressed() {
        if (isOpenDrawer()) {
            closeDrawer();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void initView() {
        initViewModel();
        initViewPager();
        enableDrawer(true);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MasterViewModel.class);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
        binding.setMasterViewModel(mViewModel);
    }

    private void initViewPager() {
        masterAdapter = new MasterAdapter(getChildFragmentManager());
        binding.masterContainer.setAdapter(masterAdapter);
        binding.masterContainer.setOffscreenPageLimit(4);
    }

    @Override
    public void initListener() {
        binding.bottomBar.setOnBottomBarClickListener(position -> {
            if (position == NOTIFICATION_FRAGMENT) {
                //hide badge
                binding.bottomBar.handleNotification(false);
            }
            binding.masterContainer.setCurrentItem(position, true);
            setToolbar(position);
        });
        binding.bottomBar.onTabClick(0);
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.tv_menu:
                    openDrawer();
                    break;
            }
        });
        mainViewModel.getNotificationBadge().observe(this, response -> {
            binding.bottomBar.handleNotification(response);
        });
    }

    private void setToolbar(int position) {
        switch (position) {
            case HOME_FRAGMENT:
                binding.toolbar.showLogoApp();
                binding.toolbar.setToolbarBackground(R.color.md_white_1000);
                binding.toolbar.setColorMenuPrimary();
                break;
            case FAVORITE_FRAGMENT:
            case SEARCH_FRAGMENT:
            case NOTIFICATION_FRAGMENT:
            case PROFILE_FRAGMENT:
                binding.toolbar.hideLogoApp();
                binding.toolbar.setToolbarBackground(R.color.colorPrimary);
                binding.toolbar.setColorMenuWhite();
                break;
        }
    }

    @Override
    public void initData() {
    }
}
