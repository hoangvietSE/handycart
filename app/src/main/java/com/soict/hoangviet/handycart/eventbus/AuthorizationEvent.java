package com.soict.hoangviet.handycart.eventbus;

public class AuthorizationEvent {
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
