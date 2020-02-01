package com.soict.hoangviet.handycart.ui.detailproduct;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.DetailProductAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentDetailProductBinding;
import com.soict.hoangviet.handycart.entity.response.DetailProductResponse;
import com.soict.hoangviet.handycart.ui.detailproduct.description.DescriptionProductFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.guide.GuideProductFragment;

import java.util.ArrayList;

public class DetailProductFragment extends BaseFragment<FragmentDetailProductBinding> {
    private static final int TAB_DESCRIPTION = 0;
    private static final int TAB_GUIDE = 1;
    public static final String EXTRA_PRODUCT_ID = "extra_product_id";
    private ArrayList<TextView> textViewArrayList = new ArrayList<>();
    private Typeface fontRegular;
    private Typeface fontBold;
    private DetailProductAdapter mDetailProductAdapter;
    private DetailProductViewModel mViewModel;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_product;
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
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailProductViewModel.class);
        mViewModel.setDetailProduct(getProductId());
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
                    getViewController().backFromAddFragment(null);
            }
        });
        mViewModel.getDetailProduct().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.btnBuyProduct.setOnClickListener(view -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
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
        if (data instanceof DetailProductResponse) {
            binding.setDetailProductResponse((DetailProductResponse) data);
            initDetailProductAdapter((DetailProductResponse) data);
        }
    }
}
