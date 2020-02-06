package com.soict.hoangviet.handycart.ui.multilanguage;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentMultiLanguageBinding;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;
import com.soict.hoangviet.handycart.utils.Define;
import com.soict.hoangviet.handycart.utils.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MultiLanguageFragment extends BaseFragment<FragmentMultiLanguageBinding> {
    private static final int FLAG_KR = 0;
    private static final int FLAG_VN = 1;
    private MultiLanguageViewModel mViewModel;
    private List<ImageView> imageViewList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multi_language;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        initViewModel();
        initListImageView();
    }

    private void initListImageView() {
        imageViewList = new ArrayList<>();
        imageViewList.add(binding.imvFlagKoren);
        imageViewList.add(binding.imvFlagVietnam);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MultiLanguageViewModel.class);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        binding.imvFlagKoren.setOnClickListener(view -> {
            onFlagClick(FLAG_KR);
            setCurrentLanguage(Define.Languages.KOREA);
        });
        binding.imvFlagVietnam.setOnClickListener(view -> {
            onFlagClick(FLAG_VN);
            setCurrentLanguage(Define.Languages.VIETNAM);
        });
    }


    private void onFlagClick(int positonFlag) {
        for (int index = 0; index < imageViewList.size(); index++) {
            if (index == positonFlag) {
                imageViewList.get(index).setBackground(getContext().getDrawable(R.drawable.bg_flag));
            } else {
                imageViewList.get(index).setBackgroundResource(0);
            }
        }
        showLoadingChangeLanguage();
    }

    private void showLoadingChangeLanguage(){
        binding.cslLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoadingChangeLanguage(){
        binding.cslLoading.setVisibility(View.INVISIBLE);
    }

    private void setCurrentLanguage(String codeLocale) {
        mViewModel.getmSharePreference().setCurrentLanguage(codeLocale);
        LanguageUtil.setCurrentLanguage(getContext(), codeLocale);
        goToHomeScreen();
    }

    private void goToHomeScreen() {
        mViewController.replaceFragment(MasterFragment.class, null);
    }
}
