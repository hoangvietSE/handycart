package com.soict.hoangviet.handycart.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    private ProfileViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel.class);
        binding.setProfileViewModel(mViewModel);
    }

    @Override
    public void initData() {
        checkLogin();
    }

    private void checkLogin() {
        mViewModel.isLogin();
    }

    @Override
    public void initListener() {
        mViewModel.getIsLogin().observe(this, login -> {
            binding.rowLogin.setDetail(login);
        });
    }
}
