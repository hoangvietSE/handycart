package com.soict.hoangviet.handycart.ui.login;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentLoginBinding;
import com.soict.hoangviet.handycart.utils.ToastUtil;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {
    private LoginViewModel loginViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.getValidateLogin().observe(this, error -> ToastUtil.show(getContext(), error));
    }
}
