package com.beetech.ec.tienichmuasam.eventbus;

public class AuthorizationEvent {
    private boolean isLogin;

    public AuthorizationEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
