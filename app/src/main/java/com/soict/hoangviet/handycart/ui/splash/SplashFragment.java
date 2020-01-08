package com.soict.hoangviet.handycart.ui.splash;

import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.SplashFragmentBinding;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;


public class SplashFragment extends BaseFragment<SplashFragmentBinding> {

    private static final long SPLASH_TIME = 1900;
    private SplashViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
        CommonExtensionUtil.loadImageDrawable(getContext(), binding.imvSplash, getContext().getDrawable(R.drawable.splash));
    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> {
            mViewController.addFragment(MasterFragment.class, null);
        }, SPLASH_TIME);
    }

}
