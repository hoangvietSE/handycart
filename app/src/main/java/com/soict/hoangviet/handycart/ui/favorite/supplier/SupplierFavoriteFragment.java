package com.soict.hoangviet.handycart.ui.favorite.supplier;

import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.SupplierFavoriteAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentSupplierFavoriteBinding;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;
import com.soict.hoangviet.handycart.eventbus.FavoriteSupplierEvent;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteSupplierListener;
import com.soict.hoangviet.handycart.ui.home.HomeViewModel;
import com.soict.hoangviet.handycart.utils.Define;

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
        mSupplierFavoriteAdapter = new SupplierFavoriteAdapter(getContext(), new FavoriteSupplierListener() {
            @Override
            public void onFavoriteClick(int position) {
                try {
                    SupplierFavoriteResponse data = mSupplierFavoriteAdapter.getItem(position, SupplierFavoriteResponse.class);
                    mViewModel.deleteSupplierFromFavorite(position, data.getId());
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

            @Override
            public void onDetailClick(int position) {

            }
        }, false);
        binding.rcvSupplierFavorite.setOnLoadingMoreListener(() -> {

        });
        binding.rcvSupplierFavorite.setOnRefreshListener(() -> {
            refreshData();
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
        mViewModel.getSupplierFavoriteDelete().observe(this, response -> {
            handleObjectResponse(response);
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
    protected <U> void getObjectResponse(U data) {
        if(data instanceof Integer){
            mSupplierFavoriteAdapter.removeModel(((Integer) data).intValue());
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
        if (authorizationEvent.isLogin()) {
            showFavorite();
            refreshData();
        } else {
            hideFavorite();
        }
        EventBus.getDefault().removeStickyEvent(authorizationEvent);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCategoryChangeEvent(FavoriteSupplierEvent favoriteSupplierEvent) {
        if (!favoriteSupplierEvent.isHomeScreen()) {
            refreshData();
        }
        EventBus.getDefault().removeStickyEvent(favoriteSupplierEvent);
    }

    private void hideFavorite() {
        binding.rcvSupplierFavorite.setVisibility(View.GONE);
    }

    private void showFavorite() {
        binding.rcvSupplierFavorite.setVisibility(View.VISIBLE);
    }

    private void refreshData() {
        mViewModel.setListSupplierFavorite(true);
    }
}
