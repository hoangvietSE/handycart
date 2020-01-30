package com.soict.hoangviet.handycart.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentProfileBinding;
import com.soict.hoangviet.handycart.eventbus.AuthorizationEvent;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
