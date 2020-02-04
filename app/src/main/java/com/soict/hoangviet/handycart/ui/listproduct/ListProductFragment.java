package com.soict.hoangviet.handycart.ui.listproduct;

import android.animation.Animator;
import android.os.Handler;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.ListProductAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.custom.CircleAnimationUtil;
import com.soict.hoangviet.handycart.custom.HomeItemDecoration;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentListProductBinding;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.CartResponse;
import com.soict.hoangviet.handycart.entity.response.HomeProductResponse;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductFragment;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteProductListener;
import com.soict.hoangviet.handycart.ui.home.HomeViewModel;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.DialogUtil;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class ListProductFragment extends BaseFragment<FragmentListProductBinding> {
    public static final String EXTRA_CATEGORY_ID = "extra_category_id";
    @Inject
    public ISharePreference mSharePreference;
    private ListProductViewModel mViewModel;
    private HomeViewModel mHomeViewModel;
    private ListProductAdapter mListProductAdapter;
    private HomeItemDecoration mHomeItemDecoration;
    private int tempPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_product;
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
        initHomeDecoration();
    }

    private void initHomeDecoration() {
        mHomeItemDecoration = new HomeItemDecoration(getResources().getDimension(R.dimen.content_padding_8_dp));
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListProductViewModel.class);
        mHomeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(()->{
            initListProductAdapter();
            getData(false);
        },300);
    }

    private void getData(boolean isRefresh) {
        getProduct(isRefresh);
        if (mSharePreference.isLogin()) {
            getCartAmountWithAuth();
        } else {
            getCartAmountNoAuth();
        }
    }

    private void getProduct(boolean isRefresh) {
        if (mSharePreference.isLogin()) {
            getListProductWithAuth(isRefresh);
        } else {
            getListProductNoAuth(isRefresh);
        }
    }


    private void getCartAmountNoAuth() {
        mHomeViewModel.getCartAmountNoAuth();
    }

    private void getCartAmountWithAuth() {
        mHomeViewModel.getCartAmountWithAuth();
    }

    private void getListProductWithAuth(boolean isRefreshing) {
        mHomeViewModel.setListHomeProductWithAuth(isRefreshing, -1);
    }

    private void getListProductNoAuth(boolean isRefreshing) {
        mHomeViewModel.setListHomeProductNoAuth(isRefreshing, -1);
    }

    @Override
    public void initListener() {
        mHomeViewModel.getListHomeProduct().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
        mHomeViewModel.getCartAmount().observe(this, response -> {
            handleObjectResponse(response);
        });
        mHomeViewModel.getFavoriteProduct().observe(this, response -> {
            handleObjectResponse(response);
        });
        mHomeViewModel.getFavoriteProductDelete().observe(this, response -> {
            handleObjectResponse(response);
        });
        mHomeViewModel.getCartTransaction().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    getViewController().backFromAddFragment(null);
                    break;
            }
        });
    }

    private void initListProductAdapter() {
        mListProductAdapter = new ListProductAdapter(getContext(), new FavoriteProductListener() {
            @Override
            public void onCartClick(ImageView imageView, int position, int quantity) {
                makeFlyAnimation(imageView, position, quantity);
            }

            @Override
            public void onDetailClick(int position) {
                HomeProductResponse data = mListProductAdapter.getItem(position, HomeProductResponse.class);
                HashMap<String, Integer> hashMap = new HashMap<>();
                hashMap.put(DetailProductFragment.EXTRA_PRODUCT_ID, data.getId());
                getViewController().addFragment(DetailProductFragment.class, hashMap);
            }

            @Override
            public void onFavoriteClick(int position) {
                if (mSharePreference.isLogin()) {
                    tempPosition = position;
                    try {
                        HomeProductResponse data = mListProductAdapter.getItem(position, HomeProductResponse.class);
                        if (data.getFlagFavorite() == Define.Favorite.STATUS_UNLIKE) {
                            mHomeViewModel.addToFavorite(data);
                        } else {
                            mHomeViewModel.deleteFromFavorite(data);
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
        binding.rcvListProduct.setGridLayoutManager(2);
        binding.rcvListProduct.setOnLoadingMoreListener(() -> {
            getProduct(false);
        });
        binding.rcvListProduct.setOnRefreshListener(() -> {
            getData(true);
        });
        binding.rcvListProduct.setAdapter(mListProductAdapter);
        binding.rcvListProduct.addItemDecoration(mHomeItemDecoration);
    }

    private void makeFlyAnimation(ImageView imageView, int position, int quantity) {
        new CircleAnimationUtil().attachActivity(getActivity()).setTargetView(imageView).setMoveDuration(1000).setDestView(binding.carts.imvCart).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                HomeProductResponse data = mListProductAdapter.getItem(position, HomeProductResponse.class);
                if (mSharePreference.isLogin()) {
                    mHomeViewModel.addToCartWithAuth(data.getId(), quantity);
                } else {
                    mHomeViewModel.addToCartNoAuth(data.getId(), quantity);
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

    @Override
    protected <U> void getObjectResponse(U data) {
        if (data instanceof HomeProductResponse) {
            if (((HomeProductResponse) data).getFlagFavorite() == Define.Favorite.STATUS_UNLIKE) {
                ((HomeProductResponse) data).setFlagFavorite(Define.Favorite.STATUS_LIKE);
                binding.rcvListProduct.updateModel(tempPosition, data, false);
            } else {
                ((HomeProductResponse) data).setFlagFavorite(Define.Favorite.STATUS_UNLIKE);
                binding.rcvListProduct.updateModel(tempPosition, data, false);
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

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvListProduct.enableLoadmore(canLoadmore);
        if (isRefresh) {
            if (data.get(0) instanceof HomeProductResponse) {
                binding.rcvListProduct.refresh(data);
            }
        } else {
            if (data.get(0) instanceof HomeProductResponse) {
                binding.rcvListProduct.addItem(data);
            }
        }
    }

}
