package com.soict.hoangviet.handycart.ui.cart;

import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.CartDetailAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentCartBinding;
import com.soict.hoangviet.handycart.entity.response.CartDetailResponse;
import com.soict.hoangviet.handycart.ui.listproduct.ListProductFragment;

import javax.inject.Inject;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    @Inject
    public ISharePreference mSharePreference;

    private CartViewModel mViewModel;
    private CartDetailAdapter cartDetailAdapter;

    @Override
    protected int getLayoutId() { return R.layout.fragment_cart; }

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
        cartDetailAdapter = new CartDetailAdapter(getContext(), false);
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
        },300);
    }

    private void getCartDetail() {
        if (mSharePreference.isLogin()) {
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
            cartDetailAdapter.addModels(((CartDetailResponse) data).getProductList(), false);
        }
    }
}
