package com.soict.hoangviet.handycart.ui.detailsupplier;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.DetailSupplierAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.custom.firebase.DynamicLinkFirebase;
import com.soict.hoangviet.handycart.data.sharepreference.ISharePreference;
import com.soict.hoangviet.handycart.databinding.FragmentDetailSupplierBinding;
import com.soict.hoangviet.handycart.entity.response.CartAmountResponse;
import com.soict.hoangviet.handycart.entity.response.DetailSupplierResponse;
import com.soict.hoangviet.handycart.ui.cart.CartFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.menu.MenuFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.service.ServiceFragment;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import java.util.ArrayList;

import javax.inject.Inject;

public class DetailSupplierFragment extends BaseFragment<FragmentDetailSupplierBinding> {
    @Inject
    public ISharePreference mSharePreference;
    private static final int TAB_MENU = 0;
    private static final int TAB_SERVICE = 1;
    private DetailSupplierViewModel mViewModel;
    private DetailSupplierAdapter detailSupplierAdapter;
    private Typeface fontRegular;
    private Typeface fontBold;
    private ArrayList<TextView> textViewArrayList = new ArrayList<>();
    public static final String EXTRA_SUPPLIER_ID = "extra_supplier_id";
    public static final String EXTRA_IS_DYNAMIC_LINK = "extra_is_dynamic_link";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_supplier;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        if (getArguments().getBoolean(EXTRA_IS_DYNAMIC_LINK)) {
            return true;
        }
        return false;
    }

    @Override
    public void initView() {
        initListTextView();
    }

    private void initListTextView() {
        textViewArrayList.add(binding.btnMenu);
        textViewArrayList.add(binding.btnService);
        fontRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/lato_regular.ttf");
        fontBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/lato_bold.ttf");
    }

    @Override
    public void initData() {
        initViewModel();
        getDataIntent();
        new Handler().postDelayed(() -> {
            getSupplierDetail();
        }, 300);
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
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    getViewController().backFromAddFragment(null);
                    break;
                case R.id.imv_right:
                    break;
            }
        });
        binding.btnMenu.setOnClickListener(view -> {
            binding.detailViewPager.setCurrentItem(TAB_MENU);
            onTabChoose(TAB_MENU);
        });
        binding.btnService.setOnClickListener(view -> {
            binding.detailViewPager.setCurrentItem(TAB_SERVICE);
            onTabChoose(TAB_SERVICE);
        });
        binding.carts.cslCart.setOnClickListener(view -> {
            getViewController().addFragment(CartFragment.class, null);
        });
        binding.llShare.setOnClickListener(view -> {
            DynamicLinkFirebase.getInstance().createDynamicLink(new DynamicLinkFirebase.DynamicLinkListener() {
                @Override
                public void onSuccess(Uri uri) {
                    setImplicitIntent(uri);
                }

                @Override
                public void onError() {
                    ToastUtil.show(getContext(), getString(R.string.handle_error));
                }
            }, "Chi tiết nhà cung cấp HandyCart", "supplierId", getArguments().getInt(EXTRA_SUPPLIER_ID, -1));
        });
    }

    private void onTabChoose(int tabDescription) {
        for (int index = 0; index < textViewArrayList.size(); index++) {
            if (tabDescription == index) {
                textViewArrayList.get(index).setTypeface(fontBold);
                textViewArrayList.get(index).setBackgroundResource(R.drawable.bg_tab_selected_detail_product);
                textViewArrayList.get(index).setTextColor(ContextCompat.getColor(getContext(), R.color.md_black_1000));
            } else {
                textViewArrayList.get(index).setTypeface(fontRegular);
                textViewArrayList.get(index).setBackgroundResource(R.drawable.bg_tab_unselected_detail_product);
                textViewArrayList.get(index).setTextColor(ContextCompat.getColor(getContext(), R.color.color_brown));
            }
        }
    }

    @Override
    protected <U> void getObjectResponse(U data) {
        if (data instanceof DetailSupplierResponse) {
            binding.setDetailSupplierResponse((DetailSupplierResponse) data);
            setToolbar(data);
            initSupplierAdapter(data);
        }
        if (data instanceof CartAmountResponse) {
            binding.carts.tvBadgeCart.setNumber(((CartAmountResponse) data).getAmount());
        }
    }

    private <U> void setToolbar(U data) {
        binding.toolbar.setToolbarTitle(((DetailSupplierResponse) data).getName());
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
                onTabChoose(position);
            }
        });
        onTabChoose(TAB_MENU);
    }

    private void setImplicitIntent(Uri shortLink) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "HandyCart");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
            startActivity(Intent.createChooser(shareIntent, "Choose one application"));
        } catch (Exception e) {
            //e.toString();
        }
    }
}
