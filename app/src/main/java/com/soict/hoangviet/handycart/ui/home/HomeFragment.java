package com.soict.hoangviet.handycart.ui.home;

import android.animation.Animator;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.BannerInfiniteAdapter;
import com.soict.hoangviet.handycart.adapter.HomeProductAdapter;
import com.soict.hoangviet.handycart.adapter.HomeSupplierAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.custom.CircleAnimationUtil;
import com.soict.hoangviet.handycart.custom.HomeItemDecoration;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentHomeBinding;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.CartResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;
import com.soict.hoangviet.handycart.eventbus.CartAmountEvent;
import com.soict.hoangviet.handycart.eventbus.FavoriteProductEvent;
import com.soict.hoangviet.handycart.eventbus.FavoriteSupplierEvent;
import com.soict.hoangviet.handycart.ui.cart.CartFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.DetailSupplierFragment;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteProductListener;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteSupplierListener;
import com.soict.hoangviet.handycart.ui.guide.GuideFragment;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.DialogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private HomeViewModel mViewModel;
    private BannerInfiniteAdapter bannerInfiniteAdapter;
    private HomeProductAdapter homeProductAdapter;
    private HomeSupplierAdapter homeSupplierAdapter;
    private int tempPosition;
    private HomeItemDecoration mHomeItemDecoration;
    public static final String EXTRA_URL = "extra_url";

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
        initHomeDecoration();
        if (mViewModel.getmSharePreference().isLogin()) {
            getListProductWithAuth(false);
            getListSupplierWithAuth(false);
            getCartAmountWithAuth();
        } else {
            getListProductNoAuth(false);
            getListSupplierNoAuth(false);
            getCartAmountNoAuth();
        }
    }

    private void initHomeDecoration() {
        mHomeItemDecoration = new HomeItemDecoration(getResources().getDimension(R.dimen.content_padding_8_dp));
    }

    private void getCartAmountNoAuth() {
        mViewModel.getCartAmountNoAuth();
    }

    private void getCartAmountWithAuth() {
        mViewModel.getCartAmountWithAuth();
    }

    private void getListSupplierWithAuth(boolean isRefreshing) {
        mViewModel.setListHomeProductWithAuth(isRefreshing, Define.Api.BaseResponse.DEFAULT_INDEX);
    }

    private void getListProductWithAuth(boolean isRefreshing) {
        mViewModel.setListHomeSupplierWithAuth(isRefreshing);
    }

    private void getListSupplierNoAuth(boolean isRefreshing) {
        mViewModel.setListHomeSupplierNoAuth(isRefreshing);
    }

    private void getListProductNoAuth(boolean isRefreshing) {
        mViewModel.setListHomeProductNoAuth(isRefreshing, Define.Api.BaseResponse.DEFAULT_INDEX);
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
        mViewModel.getListHomeProduct().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
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
        mViewModel.getCartAmount().observe(this, response -> {
            handleObjectResponse(response);
        });
        mViewModel.getCartTransaction().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.swipeRefresh.setOnRefreshListener(() -> {
            refreshData();
        });
        binding.imvBannerSmall.setOnClickListener(view -> {
            HashMap<String, String> data = new HashMap<>();
            data.put(EXTRA_URL, mViewModel.getListBanners().getValue().getUrlDiscount().get(0).getThumbnail());
            getViewController().addFragment(GuideFragment.class, data);
        });
        binding.carts.cslCart.setOnClickListener(view -> {
            getViewController().addFragment(CartFragment.class, null);
        });
    }

    public void refreshData() {
        if (mViewModel.getmSharePreference().isLogin()) {
            getListProductWithAuth(true);
            getListSupplierWithAuth(true);
            getCartAmountWithAuth();
        } else {
            getListProductNoAuth(true);
            getListSupplierNoAuth(true);
            getCartAmountNoAuth();
        }
    }

    private void refreshCartAmount(){
        if (mViewModel.getmSharePreference().isLogin()) {
            getCartAmountWithAuth();
        } else {
            getCartAmountNoAuth();
        }
    }


    @Override
    public void initData() {
        initHomeProductAdapter();
        initHomeSupplierAdapter();
    }

    private void initHomeSupplierAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeSupplierAdapter = new HomeSupplierAdapter(getContext(), new FavoriteSupplierListener() {
            @Override
            public void onFavoriteClick(int position) {
                if (mViewModel.getmSharePreference().isLogin()) {
                    tempPosition = position;
                    try {
                        HomeSupplierResponse data = homeSupplierAdapter.getItem(position, HomeSupplierResponse.class);
                        if (data.getFlagFav() == Define.Favorite.STATUS_UNLIKE) {
                            mViewModel.addSupplierToFavorite(data);
                        } else {
                            mViewModel.deleteSupplierFromFavorite(data);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
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
            }

            @Override
            public void onDetailClick(int position) {
                HomeSupplierResponse data = homeSupplierAdapter.getItem(position, HomeSupplierResponse.class);
                HashMap<String, Integer> hashMap = new HashMap<>();
                hashMap.put(DetailSupplierFragment.EXTRA_SUPPLIER_ID, data.getId());
                getViewController().addFragment(DetailSupplierFragment.class, hashMap);
            }
        }, false);
        binding.rcvHomeSupplier.setLayoutManager(layoutManager);
        homeSupplierAdapter.setLoadingMoreListener(() -> {

        });
        homeSupplierAdapter.addOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        binding.rcvHomeSupplier.setAdapter(homeSupplierAdapter);
        binding.rcvHomeSupplier.addItemDecoration(mHomeItemDecoration);
    }

    private void makeFlyAnimation(ImageView imageView, int position, int quantity) {
        new CircleAnimationUtil().attachActivity(getActivity()).setTargetView(imageView).setMoveDuration(1000).setDestView(binding.carts.imvCart).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                HomeProductResponse data = homeProductAdapter.getItem(position, HomeProductResponse.class);
                if (mViewModel.getmSharePreference().isLogin()) {
                    mViewModel.addToCartWithAuth(data.getId(), quantity);
                } else {
                    mViewModel.addToCartNoAuth(data.getId(), quantity);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();
    }

    private void initHomeProductAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        homeProductAdapter = new HomeProductAdapter(getContext(), new FavoriteProductListener() {
            @Override
            public void onCartClick(ImageView imageView, int position, int quantity) {
                makeFlyAnimation(imageView, position, quantity);
            }

            @Override
            public void onDetailClick(int position) {
                HomeProductResponse data = homeProductAdapter.getItem(position, HomeProductResponse.class);
                HashMap<String, Integer> hashMap = new HashMap<>();
                hashMap.put(DetailProductFragment.EXTRA_PRODUCT_ID, data.getId());
                getViewController().addFragment(DetailProductFragment.class, hashMap);
            }

            @Override
            public void onFavoriteClick(int position) {
                if (mViewModel.getmSharePreference().isLogin()) {
                    tempPosition = position;
                    try {
                        HomeProductResponse data = homeProductAdapter.getItem(position, HomeProductResponse.class);
                        if (data.getFlagFavorite() == Define.Favorite.STATUS_UNLIKE) {
                            mViewModel.addToFavorite(data);
                        } else {
                            mViewModel.deleteFromFavorite(data);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
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
            }
        }, false);
        binding.rcvHomeProduct.setLayoutManager(layoutManager);
        homeProductAdapter.setLoadingMoreListener(() -> {

        });
        homeProductAdapter.addOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        binding.rcvHomeProduct.setAdapter(homeProductAdapter);
        binding.rcvHomeProduct.addItemDecoration(mHomeItemDecoration);
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onFavoriteSupplierChangeEvent(FavoriteSupplierEvent favoriteSupplierEvent) {
        if (favoriteSupplierEvent.isHomeScreen()) {
            refreshData();
        }
        EventBus.getDefault().removeStickyEvent(favoriteSupplierEvent);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onFavoriteproductChangeEvent(FavoriteProductEvent favoriteProductEvent) {
        if (favoriteProductEvent.isHomeScreen()) {
            refreshData();
        }
        EventBus.getDefault().removeStickyEvent(favoriteProductEvent);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCartAmountEvent(CartAmountEvent cartAmountEvent) {
        refreshCartAmount();
        EventBus.getDefault().removeStickyEvent(cartAmountEvent);
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
            return;
        }
        if (data instanceof CartAmountResponse) {
            binding.carts.tvBadgeCart.setNumber(((CartAmountResponse) data).getAmount());
            return;
        }
        if (data instanceof CartResponse) {
            binding.carts.tvBadgeCart.setNumber(((CartResponse) data).getTotalProduct());
            return;
        }
    }
}
