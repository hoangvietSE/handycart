package com.soict.hoangviet.handycart.ui.favorite.product;

import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.ProductFavoriteAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentProductFavoriteBinding;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;
import com.soict.hoangviet.handycart.eventbus.FavoriteProductEvent;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteProductListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        initRecyclerView();
    }

    private void initRecyclerView() {

    }

    private void initAdapter() {
        mProductFavoriteAdapter = new ProductFavoriteAdapter(getContext(), new FavoriteProductListener() {
            @Override
            public void onCartClick(ImageView imageView, int position, int quantity) {

            }

            @Override
            public void onDetailClick(int position) {

            }

            @Override
            public void onFavoriteClick(int position) {
                try {
                    ProductFavoriteResponse data = mProductFavoriteAdapter.getItem(position, ProductFavoriteResponse.class);
                    mViewModel.deleteProductFromFavorite(position, data.getId());
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }, false);
        binding.rcvProductFavorite.setOnLoadingMoreListener(() -> {
            mViewModel.setListProductFavorite(false);
        });
        binding.rcvProductFavorite.setOnRefreshListener(() -> {
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

    private void refreshData() {
        mViewModel.setListProductFavorite(true);
    }

    @Override
    public void initListener() {
        mViewModel.getProductFavorite().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
        mViewModel.getProductFavoriteDelete().observe(this, response -> {
            handleObjectResponse(response);
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

    @Override
    protected <U> void getObjectResponse(U data) {
        if(data instanceof Integer){
            mProductFavoriteAdapter.removeModel(((Integer) data).intValue());
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
        binding.setProductFavoriteViewModel(mViewModel);
        if (authorizationEvent.isLogin()) {
            showFavorite();
            refreshData();
        } else {
            hideFavorite();
        }
        EventBus.getDefault().removeStickyEvent(authorizationEvent);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onFavoriteChangeEvent(FavoriteProductEvent favoriteProductEvent) {
        if (!favoriteProductEvent.isHomeScreen()) {
            refreshData();
        }
        EventBus.getDefault().removeStickyEvent(favoriteProductEvent);
    }

    private void hideFavorite() {
        binding.rcvProductFavorite.setVisibility(View.GONE);
    }

    private void showFavorite() {
        binding.rcvProductFavorite.setVisibility(View.VISIBLE);
    }
}
