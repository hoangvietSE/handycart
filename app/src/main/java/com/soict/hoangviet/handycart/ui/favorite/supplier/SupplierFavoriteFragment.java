package com.soict.hoangviet.handycart.ui.favorite.supplier;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentSupplierFavoriteBinding;

public class SupplierFavoriteFragment extends BaseFragment<FragmentSupplierFavoriteBinding> {
    private SupplierFavoriteViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_supplier_favorite;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SupplierFavoriteViewModel.class);
        binding.setSupplierFavoriteViewModel(mViewModel);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
