package com.soict.hoangviet.handycart.ui.master;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.CategoryAdapter;
import com.soict.hoangviet.handycart.adapter.MasterAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.databinding.FragmentMasterBinding;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.ui.profile.ProfileFragment;

public class MasterFragment extends BaseFragment<FragmentMasterBinding> {
    private static final int HOME_FRAGMENT = 0;
    private static final int FAVORITE_FRAGMENT = 1;
    private static final int SEARCH_FRAGMENT = 2;
    private static final int NOTIFICATION_FRAGMENT = 3;
    private static final int PROFILE_FRAGMENT = 4;

    private MasterViewModel mViewModel;
    private MasterAdapter masterAdapter;
    private CategoryAdapter categoryAdapter;

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
                    try {
                        Boolean loginResult = getArguments().getBoolean(ProfileFragment.LOGIN_RESULT);
                        if(loginResult)((HomeFragment) page).refreshData();
                        getArguments().clear();
                        break;
                    }catch (Exception e){
                    }
                    break;
                case FAVORITE_FRAGMENT:
                    break;
                case SEARCH_FRAGMENT:
                    break;
                case NOTIFICATION_FRAGMENT:
                    break;
                case PROFILE_FRAGMENT:
                    try {
                        Boolean loginResult = getArguments().getBoolean(ProfileFragment.LOGIN_RESULT);
                        ((ProfileFragment) page).setLoginResult(loginResult);
                        getArguments().clear();
                        break;
                    }catch (Exception e){
                    }
            }
        }
    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        initViewModel();
        initViewPager();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MasterViewModel.class);
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
            binding.masterContainer.setCurrentItem(position, true);
            setToolbar(position);
        });
        binding.bottomBar.onTabClick(0);
        mViewModel.getListCategory().observe(this, response -> {
            initCategoryAdapter(response);
        });
    }

    private void initCategoryAdapter(ListResponse<CategoryResponse> response) {
        categoryAdapter = new CategoryAdapter(getContext(), response.getData());
        binding.navigation.elvCategory.setAdapter(categoryAdapter);
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
        getListCategory();
    }

    private void getListCategory() {
        mViewModel.setListCategory();
    }
}
