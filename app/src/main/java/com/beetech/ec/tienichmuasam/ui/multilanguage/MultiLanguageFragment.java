package com.beetech.ec.tienichmuasam.ui.multilanguage;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentMultiLanguageBinding;
import com.beetech.ec.tienichmuasam.eventbus.CategoryProductEvent;
import com.beetech.ec.tienichmuasam.ui.main.MainActivity;
import com.beetech.ec.tienichmuasam.ui.master.MasterFragment;
import com.beetech.ec.tienichmuasam.utils.Define;
import com.beetech.ec.tienichmuasam.utils.LanguageUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MultiLanguageFragment extends BaseFragment<FragmentMultiLanguageBinding> {
    private static final int FLAG_KR = 0;
    private static final int FLAG_VN = 1;
    public static final String IS_SETTING = "is_setting";
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
        if (getArguments() != null && getArguments().getBoolean(IS_SETTING)) {
            getViewController().backFromAddFragment(null);
            return false;
        }
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

    private void showLoadingChangeLanguage() {
        binding.cslLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoadingChangeLanguage() {
        binding.cslLoading.setVisibility(View.INVISIBLE);
    }

    private void setCurrentLanguage(String codeLocale) {
        mViewModel.getmSharePreference().setCurrentLanguage(codeLocale);
        LanguageUtil.setCurrentLanguage(getContext(), codeLocale);
        if (getArguments() != null && getArguments().getBoolean(IS_SETTING)) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            goToHomeScreen();
        }
    }

    private void goToHomeScreen() {
        EventBus.getDefault().postSticky(new CategoryProductEvent(true));
        mViewController.replaceFragment(MasterFragment.class, null);
    }
}
