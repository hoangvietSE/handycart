package com.soict.hoangviet.handycart.ui.favorite;

import android.widget.ImageView;

public interface FavoriteProductListener extends FavoriteSupplierListener {
    void onCartClick(ImageView imageView, int position, int quantity);
    void onDetailClick(int position);
}
