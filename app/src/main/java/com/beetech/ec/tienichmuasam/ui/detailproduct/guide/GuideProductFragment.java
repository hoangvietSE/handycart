package com.beetech.ec.tienichmuasam.ui.detailproduct.guide;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentDetailProductGuideBinding;
import com.beetech.ec.tienichmuasam.databinding.FragmentGuideBinding;

public class GuideProductFragment extends BaseFragment<FragmentDetailProductGuideBinding> {
    public static final String EXTRA_GUIDE = "extra_guide";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_product_guide;
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
        binding.guideWebView.loadDataWithBaseURL("", getArguments().getString(EXTRA_GUIDE), mimeType, encoding, "");
    }

    @Override
    public void initListener() {

    }
}
