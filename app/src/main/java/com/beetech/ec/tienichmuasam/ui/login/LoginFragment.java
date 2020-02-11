package com.beetech.ec.tienichmuasam.ui.login;

import androidx.lifecycle.ViewModelProviders;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentLoginBinding;
import com.beetech.ec.tienichmuasam.utils.ToastUtil;

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
