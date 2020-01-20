package com.soict.hoangviet.handycart.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentProfileBinding;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import java.util.HashMap;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    public static final String LOGIN_RESULT = "login_result";
    private ProfileViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void backFromAddFragment() {
    }

    public void setLoginResult(boolean loginResult){
        if(loginResult){
            ToastUtil.show(getContext(), getString(R.string.login_success_request));
            binding.rowLogin.setDetail("Đăng xuất");
        }else{
            binding.rowLogin.setDetail("Đăng nhập");
        }
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
        mViewModel.loginFunction();
    }

    @Override
    public void initListener() {
        binding.rowLogin.setOnClickListener(view -> {
            getViewController().addFragment(LoginFragment.class, null);
        });
        mViewModel.getIsLogin().observe(this, login -> {
            binding.rowLogin.setDetail(login);
        });
    }
}
