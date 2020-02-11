package com.beetech.ec.tienichmuasam.ui.guide;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentGuideBinding;
import com.beetech.ec.tienichmuasam.ui.home.HomeFragment;
import com.beetech.ec.tienichmuasam.utils.CommonExtensionUtil;

public class GuideFragment extends BaseFragment<FragmentGuideBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guide;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        getViewController().backFromAddFragment(null);
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        getUrlThumbnail();
    }

    private void getUrlThumbnail() {
        String url = getArguments().getString(HomeFragment.EXTRA_URL);
        if (url != null) {
//            binding.webViewGuide.getSettings().setJavaScriptEnabled(true);
//            binding.webViewGuide.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//            binding.webViewGuide.getSettings().setLoadWithOverviewMode(true);
//            binding.webViewGuide.getSettings().setUseWideViewPort(true);
//            binding.webViewGuide.setWebViewClient(new WebViewClient(){
//                @Override
//                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                    handler.proceed();
//                }
//            });
//            binding.webViewGuide.loadUrl(url);
            CommonExtensionUtil.loadImageDrawable(binding.imvGuide, url);
        }
    }

    @Override
    public void initListener() {
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    getViewController().backFromAddFragment(null);
            }
        });
    }
}
