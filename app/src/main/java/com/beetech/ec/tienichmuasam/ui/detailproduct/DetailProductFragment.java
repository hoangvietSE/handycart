package com.beetech.ec.tienichmuasam.ui.detailproduct;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.adapter.DetailProductAdapter;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.custom.BottomSheetFragment;
import com.beetech.ec.tienichmuasam.custom.firebase.DynamicLinkFirebase;
import com.beetech.ec.tienichmuasam.databinding.FragmentDetailProductBinding;
import com.beetech.ec.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.ec.tienichmuasam.ui.detailproduct.description.DescriptionProductFragment;
import com.beetech.ec.tienichmuasam.ui.detailproduct.guide.GuideProductFragment;
import com.beetech.ec.tienichmuasam.utils.ToastUtil;

import java.util.ArrayList;

public class DetailProductFragment extends BaseFragment<FragmentDetailProductBinding> {
    private static final int TAB_DESCRIPTION = 0;
    private static final int TAB_GUIDE = 1;
    public static final String EXTRA_PRODUCT_ID = "extra_product_id";
    public static final String EXTRA_IS_DYNAMIC_LINK = "extra_is_dynamic_link";
    private ArrayList<TextView> textViewArrayList = new ArrayList<>();
    private Typeface fontRegular;
    private Typeface fontBold;
    private DetailProductAdapter mDetailProductAdapter;
    private DetailProductViewModel mViewModel;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetFragment mBottomSheetFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_product;
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
        setBaseToolbar();
        initListTextView();
        initBottomSheetBahavor();
    }

    private void initBottomSheetBahavor() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.sheets.bottomSheetLayout);
    }

    private void initListTextView() {
        textViewArrayList.add(binding.btnDescription);
        textViewArrayList.add(binding.btnGuide);
        fontRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/lato_regular.ttf");
        fontBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/lato_bold.ttf");
    }

    private void setBaseToolbar() {
        binding.toolbar.setLeftButtonColor(R.color.color_brown);
        binding.toolbar.setToolbarTitleColor(R.color.color_brown);
    }

    @Override
    public void initData() {
        initViewModel();
        new Handler().postDelayed(() -> {
            getDetailProduct();
        }, 300);
    }

    private void getDetailProduct() {
        mViewModel.setDetailProduct(getProductId());
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailProductViewModel.class);
    }

    private int getProductId() {
        return getArguments().getInt(EXTRA_PRODUCT_ID, -1);
    }

    private void initDetailProductAdapter(DetailProductResponse data) {
        Bundle descriptionBundle = new Bundle();
        descriptionBundle.putString(DescriptionProductFragment.EXTRA_DESCRIPTION, data.getDescription());
        Bundle guideBundle = new Bundle();
        guideBundle.putString(GuideProductFragment.EXTRA_GUIDE, data.getManufaction());
        mDetailProductAdapter = new DetailProductAdapter(getChildFragmentManager(), descriptionBundle, guideBundle);
        binding.detailViewPager.setAdapter(mDetailProductAdapter);
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
        onTabChoose(TAB_DESCRIPTION);
    }

    @Override
    public void initListener() {
        binding.btnDescription.setOnClickListener(view -> {
            binding.detailViewPager.setCurrentItem(TAB_DESCRIPTION);
            onTabChoose(TAB_DESCRIPTION);
        });
        binding.btnGuide.setOnClickListener(view -> {
            binding.detailViewPager.setCurrentItem(TAB_GUIDE);
            onTabChoose(TAB_GUIDE);
        });
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    backPressed();
                    break;
                case R.id.imv_right:
                    shareDynamicLink();
                    break;
            }
        });
        mViewModel.getDetailProduct().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.btnBuyProduct.setOnClickListener(view -> {
            mBottomSheetFragment.show(getChildFragmentManager(), mBottomSheetFragment.getTag());
        });
    }

    private void shareDynamicLink() {
        DynamicLinkFirebase.getInstance().createDynamicLink(new DynamicLinkFirebase.DynamicLinkListener() {
            @Override
            public void onSuccess(Uri uri) {
                setImplicitIntent(uri);
            }

            @Override
            public void onError() {
                ToastUtil.show(getContext(), getString(R.string.handle_error));
            }
        }, "Chi tiết sản phẩm HanyCart", "productId", getArguments().getInt(EXTRA_PRODUCT_ID, -1));
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
        if (data instanceof DetailProductResponse) {
            binding.setDetailProductResponse((DetailProductResponse) data);
            initDetailProductAdapter((DetailProductResponse) data);
            initBottomSheetDialogFragment((DetailProductResponse) data);
        }
    }

    private void initBottomSheetDialogFragment(DetailProductResponse data) {
        mBottomSheetFragment = BottomSheetFragment.newInstance(mViewModel, data);
        mBottomSheetFragment.setCancelable(true);
    }
}
