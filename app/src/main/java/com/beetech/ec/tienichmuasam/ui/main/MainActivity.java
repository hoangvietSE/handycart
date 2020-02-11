package com.beetech.ec.tienichmuasam.ui.main;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.adapter.CategoryAdapter;
import com.beetech.ec.tienichmuasam.base.BaseActivity;
import com.beetech.ec.tienichmuasam.base.ListResponse;
import com.beetech.ec.tienichmuasam.databinding.ActivityMainBinding;
import com.beetech.ec.tienichmuasam.entity.response.CategoryResponse;
import com.beetech.ec.tienichmuasam.entity.response.SubCategoriesItem;
import com.beetech.ec.tienichmuasam.eventbus.CategoryProductEvent;
import com.beetech.ec.tienichmuasam.ui.listproduct.ListProductFragment;
import com.beetech.ec.tienichmuasam.ui.master.MasterFragment;
import com.beetech.ec.tienichmuasam.ui.splash.SplashFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

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
        if(getViewController().getCurrentFragment() instanceof MasterFragment){
            getViewController().addFragment(tClass, data);
        }else{
            getViewController().backFromAddFragment(null);
            getViewController().addFragment(tClass, data);
        }

    }

    private void initCategoryAdapter(ListResponse<CategoryResponse> response) {
        categoryAdapter = new CategoryAdapter(this, response.getData());
        binding.navigation.elvCategory.setAdapter(categoryAdapter);
    }

    private void getListCategory() {
        mViewModel.setListCategory();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCategoryProductEvent(CategoryProductEvent categoryProductEvent) {
        if(categoryProductEvent.isFirst()){
            getListCategory();
        }
        EventBus.getDefault().removeStickyEvent(categoryProductEvent);
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

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
