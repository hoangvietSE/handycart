package com.beetech.ec.tienichmuasam.ui.detailsupplier.service;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentDetailSupplierServiceBinding;

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
