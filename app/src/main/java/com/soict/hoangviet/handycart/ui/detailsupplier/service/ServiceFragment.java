package com.soict.hoangviet.handycart.ui.detailsupplier.service;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentDetailSupplierServiceBinding;

public class ServiceFragment extends BaseFragment<FragmentDetailSupplierServiceBinding> {
    public static final String EXTRA_SERVICE = "extra_service";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_supplier_service;
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
        binding.guideWebView.loadDataWithBaseURL("", getArguments().getString(EXTRA_SERVICE), mimeType, encoding, "");
    }

    @Override
    public void initListener() {

    }
}
