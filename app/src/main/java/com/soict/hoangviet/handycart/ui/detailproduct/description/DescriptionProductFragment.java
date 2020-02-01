package com.soict.hoangviet.handycart.ui.detailproduct.description;

import android.webkit.WebView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentDetailProductDescriptionBinding;

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
