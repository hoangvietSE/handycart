package com.soict.hoangviet.handycart.custom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.databinding.LayoutBottomSheetBinding;
import com.soict.hoangviet.handycart.entity.response.DetailProductResponse;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductViewModel;
import com.soict.hoangviet.handycart.utils.NumberUtil;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private static final String EXTRA_DATA = "extra_data";
    private DetailProductResponse mDetailProductResponse;
    private DetailProductViewModel mViewModel;
    LayoutBottomSheetBinding binding;

    private BottomSheetFragment() {
    }

    public static BottomSheetFragment newInstance(DetailProductViewModel detailProductViewModel, DetailProductResponse mDetailProductResponse) {
        BottomSheetFragment mBottomSheetFragment = new BottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_DATA, mDetailProductResponse);
        mBottomSheetFragment.setArguments(bundle);
        mBottomSheetFragment.setmViewModel(detailProductViewModel);
        return mBottomSheetFragment;
    }

    public DetailProductViewModel getmViewModel() {
        return mViewModel;
    }

    public void setmViewModel(DetailProductViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailProductResponse = getArguments().getParcelable(EXTRA_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_bottom_sheet, null, false);
        binding.setDetailProductResponse(mDetailProductResponse);
        mViewModel.getQuantity().setValue(binding.itemQuantity.getQuantity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.itemQuantity.setListener(item -> {
            mViewModel.getQuantity().setValue(item);
        });
        mViewModel.getQuantity().observe(this, quantity -> {
            binding.btnBuyProduct.setText(NumberUtil.handlePrice(quantity * mDetailProductResponse.getSellerPrice()) + " Mua hàng");
        });
    }
}
