package com.soict.hoangviet.handycart.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.CategoryAdapter;
import com.soict.hoangviet.handycart.base.BaseActivity;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.databinding.ActivityMainBinding;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.entity.response.SubCategoriesItem;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductFragment;
import com.soict.hoangviet.handycart.ui.listproduct.ListProductFragment;
import com.soict.hoangviet.handycart.ui.splash.SplashFragment;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

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
        enableNavigationDrawer(false);

    }

    @Override
    protected void initListener() {
        mViewModel.getListCategory().observe(this, response -> {
            initCategoryAdapter(response);
        });
        binding.navigation.elvCategory.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            SubCategoriesItem subCategoriesItem = (SubCategoriesItem) categoryAdapter.getChild(groupPosition, childPosition);
            closeDrawer();
            setData(ListProductFragment.class, subCategoriesItem.getId());
            return false;
        });
        binding.navigation.elvCategory.setOnGroupClickListener((expandableListView, view, position, id) -> {
            CategoryResponse parentLists = (CategoryResponse) categoryAdapter.getGroup(position);
            if (parentLists.getSubCategories().size() == 0) {
                closeDrawer();
                setData(ListProductFragment.class, parentLists.getId());
            }
            return false;
        });
    }

    public <T> void setData(Class<T> tClass, int categoryId) {
        HashMap<String, Integer> data = new HashMap<>();
        data.put(ListProductFragment.EXTRA_CATEGORY_ID, categoryId);
        getViewController().addFragment(tClass, data);
    }

    private void initCategoryAdapter(ListResponse<CategoryResponse> response) {
        categoryAdapter = new CategoryAdapter(this, response.getData());
        binding.navigation.elvCategory.setAdapter(categoryAdapter);
    }

    private void getListCategory() {
        mViewModel.setListCategory();
    }

    public void enableNavigationDrawer(boolean enable) {
        if (enable) {
            binding.navigationDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            binding.navigationDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public void openDrawer() {
        if (!binding.navigationDrawer.isDrawerOpen(GravityCompat.END)) {
            binding.navigationDrawer.openDrawer(GravityCompat.END);
        }
    }

    public void closeDrawer() {
        binding.navigationDrawer.closeDrawer(GravityCompat.END);
    }

    public boolean isOpenDrawer() {
        return binding.navigationDrawer.isDrawerOpen(GravityCompat.END);
    }

}
