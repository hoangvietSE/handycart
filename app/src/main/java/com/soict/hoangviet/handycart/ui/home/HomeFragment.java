package com.soict.hoangviet.handycart.ui.home;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.BannerInfiniteAdapter;
import com.soict.hoangviet.handycart.adapter.CategoryAdapter;
import com.soict.hoangviet.handycart.adapter.HomeProductAdapter;
import com.soict.hoangviet.handycart.adapter.HomeSupplierAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.base.ListResponse;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentHomeBinding;
import com.soict.hoangviet.handycart.entity.response.CategoryResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    @Inject
    public ISharePreference mSharePreference;
    private HomeViewModel mViewModel;
    private BannerInfiniteAdapter bannerInfiniteAdapter;
    private HomeProductAdapter homeProductAdapter;
    private HomeSupplierAdapter homeSupplierAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
        initViewModel();
        getListBanner();
        if (mSharePreference.isLogin()) {
            getListProductWithAuth(false);
            getListSupplierWithAuth(false);
        } else {
            getListProductNoAuth(false);
            getListSupplierNoAuth(false);
        }
    }

    private void getListSupplierWithAuth(boolean isRefreshing) {
        mViewModel.setListHomeProductWithAuth(isRefreshing);
    }

    private void getListProductWithAuth(boolean isRefreshing) {
        mViewModel.setListHomeSupplierWithAuth(isRefreshing);
    }

    private void getListSupplierNoAuth(boolean isRefreshing) {
        mViewModel.setListHomeSupplierNoAuth(isRefreshing);
    }

    private void getListProductNoAuth(boolean isRefreshing) {
        mViewModel.setListHomeProductNoAuth(isRefreshing);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
    }

    private void getListBanner() {
        mViewModel.setListBanners();
    }

    @Override
    public void initListener() {
        mViewModel.getListBanners().observe(this, bannerResponse -> {
            binding.setBanner(bannerResponse);
            bannerInfiniteAdapter = new BannerInfiniteAdapter(getContext(), bannerResponse.getData(), true);
            binding.viewpagerLooping.setAdapter(bannerInfiniteAdapter);
        });
        mViewModel.getListHomeProduct().observe(this, reponse -> {
            handleLoadMoreResponse(reponse, reponse.isRefresh(), reponse.isCanLoadmore());
        });
        mViewModel.getListHomeSupplier().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
        binding.swipeRefresh.setOnRefreshListener(() -> {
            refreshData();
        });
    }

    private void refreshData() {
        if (mSharePreference.isLogin()) {
            getListProductWithAuth(true);
            getListSupplierWithAuth(true);
        } else {
            getListProductNoAuth(true);
            getListSupplierNoAuth(true);
        }
    }

    private void initCategoryAdapter(ListResponse<CategoryResponse> response) {
        categoryAdapter = new CategoryAdapter(getContext(), response.getData());
    }

    @Override
    public void initData() {
        initHomeProductAdapter();
        initHomeSupplierAdapter();
    }

    private void initHomeSupplierAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeSupplierAdapter = new HomeSupplierAdapter(getContext(), false);
        binding.rcvHomeSupplier.setLayoutManager(layoutManager);
        homeSupplierAdapter.setLoadingMoreListener(() -> {

        });
        homeSupplierAdapter.addOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        binding.rcvHomeSupplier.setAdapter(homeSupplierAdapter);
    }

    private void initHomeProductAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeProductAdapter = new HomeProductAdapter(getContext(), false);
        binding.rcvHomeProduct.setLayoutManager(layoutManager);
        homeProductAdapter.setLoadingMoreListener(() -> {

        });
        homeProductAdapter.addOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        binding.rcvHomeProduct.setAdapter(homeProductAdapter);
    }


    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        if (isRefresh) {
            if (data.get(0) instanceof HomeProductResponse) {
                homeProductAdapter.refresh(data);
            } else if (data.get(0) instanceof HomeSupplierResponse) {
                homeSupplierAdapter.refresh(data);
            }
            hideRefreshing();
        } else {
            if (data.get(0) instanceof HomeProductResponse) {
                homeProductAdapter.addModels(data, false);
            } else if (data.get(0) instanceof HomeSupplierResponse) {
                homeSupplierAdapter.addModels(data, false);
            }
        }
    }

    private void hideRefreshing() {
        binding.swipeRefresh.hideRefreshing();
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCategoryChangeEvent(AuthorizationEvent authorizationEvent) {
        refreshData();
        EventBus.getDefault().removeStickyEvent(authorizationEvent);
    }
}
