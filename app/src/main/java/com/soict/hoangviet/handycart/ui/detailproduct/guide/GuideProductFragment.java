package com.soict.hoangviet.handycart.ui.detailproduct.guide;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentDetailProductGuideBinding;
import com.soict.hoangviet.handycart.databinding.FragmentGuideBinding;

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
