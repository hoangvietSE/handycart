package com.soict.hoangviet.handycart.ui.home;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.BannerInfiniteAdapter;
import com.soict.hoangviet.handycart.adapter.HomeProductAdapter;
import com.soict.hoangviet.handycart.adapter.HomeSupplierAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentHomeBinding;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.DialogUtil;

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
    private int tempPosition;

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
        mViewModel.getFavoriteProduct().observe(this, response -> {
            handleObjectResponse(response);
        });
        mViewModel.getFavoriteProductDelete().observe(this, response -> {
            handleObjectResponse(response);
        });
        mViewModel.getFavoriteSupplier().observe(this, response -> {
            handleObjectResponse(response);
        });
        mViewModel.getFavoriteSupplierDelete().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.swipeRefresh.setOnRefreshListener(() -> {
            refreshData();
        });
    }

    public void refreshData() {
        if (mSharePreference.isLogin()) {
            getListProductWithAuth(true);
            getListSupplierWithAuth(true);
        } else {
            getListProductNoAuth(true);
            getListSupplierNoAuth(true);
        }
    }

    @Override
    public void initData() {
        initHomeProductAdapter();
        initHomeSupplierAdapter();
    }

    private void initHomeSupplierAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeSupplierAdapter = new HomeSupplierAdapter(getContext(), position -> {
            if (mSharePreference.isLogin()) {
                tempPosition = position;
                try {
                    HomeSupplierResponse data = homeSupplierAdapter.getItem(position, HomeSupplierResponse.class);
                    if (data.getFlagFav() == Define.Favorite.STATUS_UNLIKE) {
                        mViewModel.addSupplierToFavorite(data);
                    } else {
                        mViewModel.deleteSupplierFromFavorite(data);
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                }
            } else {
                DialogUtil.showConfirmDialog(
                        getContext(),
                        R.string.favorite_title,
                        R.string.favorite_message,
                        R.string.favorite_need_login,
                        R.string.favorite_cancel,
                        (dialogInterface, which) -> {
                            getViewController().addFragment(LoginFragment.class, null);
                        },
                        (dialogInterface, which) -> {
                            dialogInterface.dismiss();
                        }
                );
            }
        }, false);
        binding.rcvHomeSupplier.setLayoutManager(layoutManager);
        homeSupplierAdapter.setLoadingMoreListener(() -> {

        });
        homeSupplierAdapter.addOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        binding.rcvHomeSupplier.setAdapter(homeSupplierAdapter);
    }

    private void initHomeProductAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeProductAdapter = new HomeProductAdapter(getContext(), position -> {
            if (mSharePreference.isLogin()) {
                tempPosition = position;
                try {
                    HomeProductResponse data = homeProductAdapter.getItem(position, HomeProductResponse.class);
                    if (data.getFlagFavorite() == Define.Favorite.STATUS_UNLIKE) {
                        mViewModel.addToFavorite(data);
                    } else {
                        mViewModel.deleteFromFavorite(data);
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                }

            } else {
                DialogUtil.showConfirmDialog(
                        getContext(),
                        R.string.favorite_title,
                        R.string.favorite_message,
                        R.string.favorite_need_login,
                        R.string.favorite_cancel,
                        (dialogInterface, which) -> {
                            getViewController().addFragment(LoginFragment.class, null);
                        },
                        (dialogInterface, which) -> {
                            dialogInterface.dismiss();
                        }
                );
            }
        }, false);
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

    @Override
    protected <U> void getObjectResponse(U data) {
        if (data instanceof HomeProductResponse) {
            if (((HomeProductResponse) data).getFlagFavorite() == Define.Favorite.STATUS_UNLIKE) {
                ((HomeProductResponse) data).setFlagFavorite(Define.Favorite.STATUS_LIKE);
                homeProductAdapter.notifyItemChanged(tempPosition);
            } else {
                ((HomeProductResponse) data).setFlagFavorite(Define.Favorite.STATUS_UNLIKE);
                homeProductAdapter.notifyItemChanged(tempPosition);
            }
            return;
        }
        if (data instanceof HomeSupplierResponse) {
            if (((HomeSupplierResponse) data).getFlagFav() == Define.Favorite.STATUS_UNLIKE) {
                ((HomeSupplierResponse) data).setFlagFav(Define.Favorite.STATUS_LIKE);
                homeSupplierAdapter.notifyItemChanged(tempPosition);
            } else {
                ((HomeSupplierResponse) data).setFlagFav(Define.Favorite.STATUS_UNLIKE);
                homeSupplierAdapter.notifyItemChanged(tempPosition);
            }
        }
    }
}
