package com.soict.hoangviet.handycart.ui.master;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.MasterAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentMasterBinding;

public class MasterFragment extends BaseFragment<FragmentMasterBinding> {
    private static final int HOME_FRAGMENT = 0;
    private static final int FAVORITE_FRAGMENT = 1;
    private static final int SEARCH_FRAGMENT = 2;
    private static final int NOTIFICATION_FRAGMENT = 3;
    private static final int PROFILE_FRAGMENT = 4;

    private MasterAdapter masterAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master;
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
        initViewPager();
    }

    private void initViewPager() {
        masterAdapter = new MasterAdapter(getChildFragmentManager());
        binding.masterContainer.setAdapter(masterAdapter);
        binding.masterContainer.setOffscreenPageLimit(4);
    }

    @Override
    public void initListener() {
        binding.bottomBar.setOnBottomBarClickListener(position -> {
            binding.masterContainer.setCurrentItem(position, true);
            setToolbar(position);
        });
        binding.bottomBar.onTabClick(0);
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
