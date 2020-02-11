package com.beetech.ec.tienichmuasam.ui.cart;

public interface CartTransactionListener {
    void onDelete(int position);
    void onChangeQuantity(int quantity, int postion);
}
