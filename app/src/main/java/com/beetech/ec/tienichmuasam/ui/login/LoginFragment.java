package com.beetech.ec.tienichmuasam.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.BaseSoicalFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentLoginBinding;
import com.beetech.ec.tienichmuasam.entity.response.FacebookResponse;
import com.beetech.ec.tienichmuasam.utils.ToastUtil;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends BaseSoicalFragment<FragmentLoginBinding> {
    private LoginViewModel loginViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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
        binding.toolbar.setOnToolbarClickListener(viewId -> {
            switch (viewId) {
                case R.id.imv_left:
                    getViewController().backFromAddFragment(null);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.getValidateLoginEmail().observe(this, error -> {
            ToastUtil.show(getContext(), error);
            binding.edtUsername.requestFocus();
        });
        loginViewModel.getValidateLoginPassword().observe(this, error -> {
            ToastUtil.show(getContext(), error);
            binding.edtPassword.requestFocus();
        });

    }

    @Override
    protected SocialGoogleListener getSocialGoogleListener() {
        return new SocialGoogleListener() {
            @Override
            public void onSuccess(GoogleSignInAccount account) {
            }

            @Override
            public void onError() {
            }
        };
    }

    @Override
    protected SocialFacebookListener getSocialFacebookListener() {
        return new SocialFacebookListener() {
            @Override
            public void onSuccess(AccessToken accessToken) {
                GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    //OnCompleted is invoked once the GraphRequest is successful
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            FacebookResponse facebookResponse = new Gson().fromJson(object.toString(), FacebookResponse.class);
                        } catch (Exception e) {
                        }
                    }
                });
                // We set parameters to the GraphRequest using a Bundle.
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,picture.width(163)");
                request.setParameters(parameters);
                // Initiate the GraphRequest
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        };
    }

    @Override
    public void initListener() {
        loginViewModel.getLogin().observe(this, response -> {
            handleObjectResponse(response);
        });
        binding.tvSocialGoogle.setOnClickListener(view -> {
            signInGoogle();
        });
        binding.tvSocialFacebook.setOnClickListener(view -> {
            signInFacebook();
        });
    }

    @Override
    protected <U> void getObjectResponse(U data) {
        getViewController().backFromAddFragment(null);
    }
}
