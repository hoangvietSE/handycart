package com.soict.hoangviet.handycart.ui.detailsupplier;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.DetailSupplierAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentDetailSupplierBinding;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.DetailSupplierResponse;
import com.soict.hoangviet.handycart.ui.detailsupplier.menu.MenuFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.service.ServiceFragment;

import java.util.List;

import javax.inject.Inject;

public class DetailSupplierFragment extends BaseFragment<FragmentDetailSupplierBinding> {
    @Inject
    public ISharePreference mSharePreference;
    private DetailSupplierViewModel mViewModel;
    private DetailSupplierAdapter detailSupplierAdapter;
    public static final String EXTRA_SUPPLIER_ID = "extra_supplier_id";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_supplier;
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
    }

    @Override
    public void initData() {
        initViewModel();
        getDataIntent();
        getSupplierDetail();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailSupplierViewModel.class);
    }

    private void getSupplierDetail() {
        if (mSharePreference.isLogin()) {
            mViewModel.getCartAmountWithAuth();
        } else {
            mViewModel.getCartAmountNoAuth();
        }
        mViewModel.setDetailSupplier(getArguments().getInt(EXTRA_SUPPLIER_ID, -1));
    }

    private void getDataIntent() {
    }

    @Override
    public void initListener() {
        mViewModel.getDetailSupplier().observe(this, response -> {
            handleObjectResponse(response);
        });
        mViewModel.getCartAmount().observe(this, response -> {
            handleObjectResponse(response);
        });
    }

    @Override
    protected <U> void getObjectResponse(U data) {
        if (data instanceof DetailSupplierResponse) {
            binding.setDetailSupplierResponse((DetailSupplierResponse) data);
            initSupplierAdapter(data);
        }
        if (data instanceof CartAmountResponse) {
            binding.carts.tvBadgeCart.setNumber(((CartAmountResponse) data).getAmount());
        }
    }

    private <U> void initSupplierAdapter(U data) {
        Bundle menuBundle = new Bundle();
        menuBundle.putInt(MenuFragment.EXTRA_SUPPLIER_ID, ((DetailSupplierResponse) data).getId());
        Bundle serviceBundle = new Bundle();
        serviceBundle.putString(ServiceFragment.EXTRA_SERVICE, ((DetailSupplierResponse) data).getMoreInformation());
        detailSupplierAdapter = new DetailSupplierAdapter(getChildFragmentManager(), menuBundle, serviceBundle);
        binding.detailViewPager.setAdapter(detailSupplierAdapter);
        binding.detailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int position) {
            }
        });
    }
}
