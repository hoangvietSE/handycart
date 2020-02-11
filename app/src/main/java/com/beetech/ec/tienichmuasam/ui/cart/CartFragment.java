package com.beetech.ec.tienichmuasam.ui.cart;

import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.adapter.CartDetailAdapter;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentCartBinding;
import com.beetech.ec.tienichmuasam.entity.response.CartDetailResponse;
import com.beetech.ec.tienichmuasam.entity.response.ProductListItem;
import com.beetech.ec.tienichmuasam.ui.listproduct.ListProductFragment;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    private CartViewModel mViewModel;
    private CartDetailAdapter cartDetailAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void backFromAddFragment() {
        enableDrawer(false);
    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    public void initView() {
        setToolbar();
        initViewModel();
        initCartDetailAdapter();
        enableDrawer(false);
    }

    private void initCartDetailAdapter() {
        cartDetailAdapter = new CartDetailAdapter(getContext(), new CartTransactionListener() {
            @Override
            public void onDelete(int position) {
                ProductListItem data = cartDetailAdapter.getItem(position, ProductListItem.class);
                mViewModel.getIsUpdateCart().setValue(false);
                if (mViewModel.getmSharePreference().isLogin()) {
                    mViewModel.deleteItemCartWithAuth(data);
                } else {
                    mViewModel.deleteItemCartNoAuth(data);
                }
            }

            @Override
            public void onChangeQuantity(int quantity, int position) {
                ProductListItem data = cartDetailAdapter.getItem(position, ProductListItem.class);
                mViewModel.getIsUpdateCart().setValue(true);
                if (mViewModel.getmSharePreference().isLogin()) {
                    mViewModel.updateCartDetailWithAuth(data, quantity);
                } else {
                    mViewModel.updateCartDetailNoAuth(data, quantity);
                }

            }
        }, false);
        binding.productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.productRecyclerView.setAdapter(cartDetailAdapter);
    }

    private void setToolbar() {
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    getViewController().backFromAddFragment(null);
                    break;
            }
        });
    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> {
            getCartDetail();
        }, 300);
    }

    private void getCartDetail() {
        if (mViewModel.getmSharePreference().isLogin()) {
            mViewModel.setCartDetailWithAuth();
        } else {
            mViewModel.setCartDetailNoAuth();
        }
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(CartViewModel.class);
    }

    @Override
    public void initListener() {
        mViewModel.getCartDetail().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.layoutBuyMore.cslBuyMore.setOnClickListener(view -> {
            getViewController().addFragment(ListProductFragment.class, null);
        });
    }

    @Override
    protected <U> void getObjectResponse(U data) {
        if (data instanceof CartDetailResponse) {
            binding.setCartDetailResponse((CartDetailResponse) data);
            binding.tvBadgeCart.setNumber(((CartDetailResponse) data).getTotalQuantity(), true);
            if (!mViewModel.getIsUpdateCart().getValue()) {
                cartDetailAdapter.refresh(((CartDetailResponse) data).getProductList());
            }
        }
    }
}
