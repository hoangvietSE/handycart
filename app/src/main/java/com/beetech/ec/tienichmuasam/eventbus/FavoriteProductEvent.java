package com.beetech.ec.tienichmuasam.eventbus;

public class FavoriteProductEvent {
    private boolean isHomeScreen;

    public FavoriteProductEvent(boolean isHomeScreen) {
        this.isHomeScreen = isHomeScreen;
    }

    public boolean isHomeScreen() {
        return isHomeScreen;
    }

    public void setHomeScreen(boolean homeScreen) {
        isHomeScreen = homeScreen;
    }
}
