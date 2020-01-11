package com.soict.hoangviet.handycart.ui.favorite.product;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentProductFavoriteBinding;
import com.soict.hoangviet.handycart.ui.favorite.supplier.SupplierFavoriteViewModel;

public class ProductFavoriteFragment extends BaseFragment<FragmentProductFavoriteBinding> {
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
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductFavoriteViewModel.class);
        binding.setProductFavoriteViewModel(mViewModel);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
