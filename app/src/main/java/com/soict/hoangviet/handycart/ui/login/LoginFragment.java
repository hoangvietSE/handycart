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
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    public void initView() {
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    getViewController().backFromAddFragment(null);
            }
        });
    }

    @Override
    public void initData() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.getValidateLoginEmail().observe(this, error -> {
            ToastUtil.show(getContext(), error);
            binding.edtUsername.requestFocus();
        });
        loginViewModel.getValidateLoginPassword().observe(this, error -> {
            ToastUtil.show(getContext(), error);
            binding.edtPassword.requestFocus();
        });
    }

    @Override
    public void initListener() {
        loginViewModel.getLogin().observe(this, response -> {
            handleObjectResponse(response);
        });
    }

    @Override
    protected <U> void getObjectResponse(U data) {
        getViewController().backFromAddFragment(null);
    }
}
