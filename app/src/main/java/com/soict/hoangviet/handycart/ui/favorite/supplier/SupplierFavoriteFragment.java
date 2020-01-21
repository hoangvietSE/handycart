package com.soict.hoangviet.handycart.ui.favorite.supplier;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.SupplierFavoriteAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentSupplierFavoriteBinding;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SupplierFavoriteFragment extends BaseFragment<FragmentSupplierFavoriteBinding> {
    private SupplierFavoriteAdapter mSupplierFavoriteAdapter;
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
        initAdapter();
    }

    private void initAdapter() {
        mSupplierFavoriteAdapter = new SupplierFavoriteAdapter(getContext(), false);
        binding.rcvSupplierFavorite.setOnLoadingMoreListener(() -> {

        });
        binding.rcvSupplierFavorite.setOnRefreshListener(() -> {
            mViewModel.setListSupplierFavorite(true);
        });
        binding.rcvSupplierFavorite.setGridLayoutManager(2);
        binding.rcvSupplierFavorite.setAdapter(mSupplierFavoriteAdapter);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SupplierFavoriteViewModel.class);
        binding.setSupplierFavoriteViewModel(mViewModel);
    }

    @Override
    public void initData() {
        mViewModel.setListSupplierFavorite(false);
    }

    @Override
    public void initListener() {
        mViewModel.getSupplierFavorite().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvSupplierFavorite.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvSupplierFavorite.refresh(data);
        } else {
            binding.rcvSupplierFavorite.addItem(data);
        }
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
        binding.setSupplierFavoriteViewModel(mViewModel);
    }
}
