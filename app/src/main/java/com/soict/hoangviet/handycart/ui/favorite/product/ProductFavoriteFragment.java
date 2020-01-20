package com.soict.hoangviet.handycart.ui.favorite.product;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.ProductFavoriteAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentProductFavoriteBinding;
import com.soict.hoangviet.handycart.ui.favorite.supplier.SupplierFavoriteViewModel;

import java.util.List;

public class ProductFavoriteFragment extends BaseFragment<FragmentProductFavoriteBinding> {
    private ProductFavoriteAdapter mProductFavoriteAdapter;
    private ProductFavoriteViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_favorite;
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
        initViewModel();
        initAdapter();
    }

    private void initAdapter() {
        mProductFavoriteAdapter = new ProductFavoriteAdapter(getContext(), false);
        binding.rcvProductFavorite.setOnLoadingMoreListener(()->{
            mViewModel.setListProductFavorite(false);
        });
        binding.rcvProductFavorite.setOnRefreshListener(()->{
            mViewModel.setListProductFavorite(true);
        });
        binding.rcvProductFavorite.setGridLayoutManager(2);
        binding.rcvProductFavorite.setAdapter(mProductFavoriteAdapter);

    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductFavoriteViewModel.class);
        binding.setProductFavoriteViewModel(mViewModel);
    }

    @Override
    public void initData() {
        mViewModel.setListProductFavorite(false);
    }

    @Override
    public void initListener() {
        mViewModel.getProductFavorite().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvProductFavorite.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvProductFavorite.refresh(data);
        } else {
            binding.rcvProductFavorite.addItem(data);
        }
    }
}
