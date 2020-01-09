package com.soict.hoangviet.handycart.ui.home;

import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.BannerInfiniteAdapter;
import com.soict.hoangviet.handycart.adapter.HomeProductAdapter;
import com.soict.hoangviet.handycart.adapter.SearchAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentHomeBinding;
import com.soict.hoangviet.handycart.entity.SearchResponse;

import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    @Inject
    public ISharePreference mSharePreference;
    private HomeViewModel mViewModel;
    private SearchAdapter searchAdapter;
    private BannerInfiniteAdapter bannerInfiniteAdapter;
    private HomeProductAdapter homeProductAdapter;

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
        }
    }

    private void getListProductNoAuth() {
        mViewModel.setListHomeProduct();
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
    }

    @Override
    public void initData() {
        initHomeAdapter();
        searchAdapter = new SearchAdapter(getContext());
        binding.rcvSearch.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvSearch.setAdapter(searchAdapter);
        binding.rcvSearch.setOnLoadingMoreListener(() -> mViewModel.search(false));

        binding.rcvSearch.setOnRefreshListener(() -> mViewModel.search(true));
        binding.rcvSearch.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {
            SearchResponse searchResponse = searchAdapter.getItem(position, SearchResponse.class);
            Toast.makeText(getContext(), searchResponse.getName() + "  " + searchResponse.getPrice(), Toast.LENGTH_SHORT).show();
        });
        mViewModel.search(true);
        mViewModel.getSearch().observe(getViewLifecycleOwner(),
                searchResponseListResponse -> handleLoadMoreResponse(searchResponseListResponse, searchResponseListResponse.isRefresh(), searchResponseListResponse.isCanLoadmore()));
    }

    private void initHomeAdapter() {
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
        binding.rcvSearch.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvSearch.refresh(data);
        } else {
            binding.rcvSearch.addItem(data);
            homeProductAdapter.addModels(data, false);
        }
    }
}
