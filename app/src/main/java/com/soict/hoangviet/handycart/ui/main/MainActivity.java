package com.soict.hoangviet.handycart.ui.main;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.CategoryAdapter;
import com.soict.hoangviet.handycart.base.BaseActivity;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.databinding.ActivityMainBinding;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.ui.splash.SplashFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private MainViewModel mViewModel;
    private CategoryAdapter categoryAdapter;

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
        initViewModel();
        mViewController.addFragment(SplashFragment.class, null);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
    }

    @Override
    public void initData() {
        getListCategory();
    }

    @Override
    protected void initListener() {
        mViewModel.getListCategory().observe(this, response -> {
            initCategoryAdapter(response);
        });
    }

    private void initCategoryAdapter(ListResponse<CategoryResponse> response) {
        categoryAdapter = new CategoryAdapter(this, response.getData());
        binding.navigation.elvCategory.setAdapter(categoryAdapter);
    }

    private void getListCategory() {
        mViewModel.setListCategory();
    }
}
