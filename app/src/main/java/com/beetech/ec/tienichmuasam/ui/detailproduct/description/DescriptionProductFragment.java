package com.beetech.ec.tienichmuasam.ui.detailproduct.description;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentDetailProductDescriptionBinding;

public class DescriptionProductFragment extends BaseFragment<FragmentDetailProductDescriptionBinding> {
    public static final String EXTRA_DESCRIPTION = "extra_description";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_product_description;
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
        getDataIntent();
    }

    private void getDataIntent() {
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        binding.descriptionWebView.loadDataWithBaseURL("", getArguments().getString(EXTRA_DESCRIPTION), mimeType, encoding, "");
    }

    @Override
    public void initListener() {

    }
}
