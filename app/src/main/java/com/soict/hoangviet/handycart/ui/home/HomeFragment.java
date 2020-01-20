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

        } else {
            getListProductNoAuth();
            getListSupplierNoAuth();
        }
    }

    private void getListSupplierNoAuth() {
        mViewModel.setListHomeSupplierNoAuth();
    }

    private void getListProductNoAuth() {
        mViewModel.setListHomeProductNoAuth();
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
    }

    private void initCategoryAdapter(ListResponse<CategoryResponse> response) {
        categoryAdapter = new CategoryAdapter(getContext(),response.getData());
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
        } else {
            if (data.get(0) instanceof HomeProductResponse) {
                homeProductAdapter.addModels(data, false);
            } else if (data.get(0) instanceof HomeSupplierResponse) {
                homeSupplierAdapter.addModels(data, false);
            }
        }
    }
}
