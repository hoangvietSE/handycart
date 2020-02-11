package com.beetech.ec.tienichmuasam.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentProfileBinding;
import com.beetech.ec.tienichmuasam.eventbus.AuthorizationEvent;
import com.beetech.ec.tienichmuasam.ui.login.LoginFragment;
import com.beetech.ec.tienichmuasam.ui.multilanguage.MultiLanguageFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {
    public static final String LOGIN_RESULT = "login_result";
    private ProfileViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void backFromAddFragment() {
    }

//    public void setLoginResult(boolean loginResult){
//        if(loginResult){
//            mViewModel.getIsVisibleLiveData().setValue(true);
//            ToastUtil.show(getContext(), getString(R.string.login_success_request));
//        }else{
//            mViewModel.getIsVisibleLiveData().setValue(false);
//        }
//    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel.class);
        binding.setProfileViewModel(mViewModel);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        binding.rowChangeLanguage.setOnClickListener(view -> {
            HashMap<String, Boolean> data = new HashMap<>();
            data.put(MultiLanguageFragment.IS_SETTING, true);
            getViewController().addFragment(MultiLanguageFragment.class, data);
        });
        binding.rowLogin.setOnClickListener(view -> {
            getViewController().addFragment(LoginFragment.class, null);
        });
        binding.rowLogout.setOnClickListener(view -> {
            mViewModel.logOut();
        });
        mViewModel.getResponseLogout().observe(this, responseLogout -> {
            handleObjectResponse(responseLogout);
        });
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onCategoryChangeEvent(AuthorizationEvent authorizationEvent) {
        mViewModel.getIsVisibleLiveData().setValue(true);
        EventBus.getDefault().removeStickyEvent(authorizationEvent);
    }
}
