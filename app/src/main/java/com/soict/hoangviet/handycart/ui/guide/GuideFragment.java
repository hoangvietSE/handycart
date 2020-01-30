package com.soict.hoangviet.handycart.ui.guide;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentGuideBinding;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;

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
            CommonExtensionUtil.loadImageDrawable(getContext(), binding.imvGuide, url);
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
