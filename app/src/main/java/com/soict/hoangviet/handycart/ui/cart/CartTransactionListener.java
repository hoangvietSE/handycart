package com.soict.hoangviet.handycart.ui.cart;

public interface CartTransactionListener {
    void onDelete(int position);
    void onChangeQuantity(int quantity, int postion);
}
