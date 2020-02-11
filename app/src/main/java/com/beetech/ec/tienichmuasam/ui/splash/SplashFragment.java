package com.beetech.ec.tienichmuasam.ui.splash;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.custom.firebase.DynamicLinkFirebase;
import com.beetech.ec.tienichmuasam.databinding.SplashFragmentBinding;
import com.beetech.ec.tienichmuasam.ui.detailproduct.DetailProductFragment;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.DetailSupplierFragment;
import com.beetech.ec.tienichmuasam.ui.main.MainActivity;
import com.beetech.ec.tienichmuasam.ui.master.MasterFragment;
import com.beetech.ec.tienichmuasam.ui.multilanguage.MultiLanguageFragment;
import com.beetech.ec.tienichmuasam.utils.CommonExtensionUtil;

import java.util.HashMap;


public class SplashFragment extends BaseFragment<SplashFragmentBinding> {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final long SPLASH_TIME = 1900;
    private SplashViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
        CommonExtensionUtil.loadImageDrawable(binding.imvSplash, getContext().getDrawable(R.drawable.splash));
    }

    @Override
    public void initData() {
        new Handler().postDelayed(() -> {
            receiveDynamicLink();
        }, SPLASH_TIME);
    }

    @Override
    public void initListener() {

    }

    private void receiveDynamicLink() {
        DynamicLinkFirebase.getInstance().receviveDynamicLink(getActivity().getIntent(), new DynamicLinkFirebase.ReceiverListener() {
            @Override
            public void onSuccess(Uri deepLink) {
                if (deepLink != null) {
                    String productId = deepLink.getQueryParameter("productId");
                    if (productId != null) {
                        showDetailProduct(productId);
                        return;
                    }
                    String supplierId = deepLink.getQueryParameter("supplierId");
                    if (supplierId != null) {
                        showSupplierDetail(supplierId);
                        return;
                    }
                } else {
                    handleMultiLanguage();
                }
            }

            @Override
            public void onError(Exception e) {
                Log.w(TAG, "getDynamicLink:onFailure", e);
            }
        });
    }

    private void handleMultiLanguage() {
        if (mViewModel.getmSharePreference().getCurrentLanguage() == "") {
            mViewController.addFragment(MultiLanguageFragment.class, null);
        } else {
            mViewController.addFragment(MasterFragment.class, null);
        }
    }

    private void showSupplierDetail(String supplierId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(DetailSupplierFragment.EXTRA_SUPPLIER_ID, Integer.valueOf(supplierId));
        hashMap.put(DetailSupplierFragment.EXTRA_IS_DYNAMIC_LINK, true);
        getViewController().replaceFragment(DetailSupplierFragment.class, hashMap);
    }

    private void showDetailProduct(String productId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(DetailProductFragment.EXTRA_PRODUCT_ID, Integer.valueOf(productId));
        hashMap.put(DetailProductFragment.EXTRA_IS_DYNAMIC_LINK, true);
        getViewController().replaceFragment(DetailProductFragment.class, hashMap);
    }

}
