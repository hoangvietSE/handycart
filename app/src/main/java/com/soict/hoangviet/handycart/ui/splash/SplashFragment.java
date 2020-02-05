package com.soict.hoangviet.handycart.ui.splash;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.custom.firebase.DynamicLinkFirebase;
import com.soict.hoangviet.handycart.databinding.SplashFragmentBinding;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.DetailSupplierFragment;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.ui.main.MainActivity;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;

import java.util.HashMap;
import java.util.concurrent.Executor;


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
                    mViewController.addFragment(MasterFragment.class, null);
                }
            }

            @Override
            public void onError(Exception e) {
                Log.w(TAG, "getDynamicLink:onFailure", e);
            }
        });
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
