package com.soict.hoangviet.handycart.ui.main;

import android.os.Bundle;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseActivity;
import com.soict.hoangviet.handycart.databinding.ActivityMainBinding;
import com.soict.hoangviet.handycart.ui.splash.SplashFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.flMainContainer;
    }

    @Override
    public void initView() {
        mViewController.addFragment(SplashFragment.class, null);
    }

    @Override
    public void initData() {

    }
}
