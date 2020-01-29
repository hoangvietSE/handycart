package com.soict.hoangviet.handycart.eventbus;

public class FavoriteSupplierEvent {
    private boolean isHomeScreen;

    public FavoriteSupplierEvent(boolean isHomeScreen) {
        this.isHomeScreen = isHomeScreen;
    }

    public boolean isHomeScreen() {
        return isHomeScreen;
    }

    public void setHomeScreen(boolean homeScreen) {
        isHomeScreen = homeScreen;
    }
}
