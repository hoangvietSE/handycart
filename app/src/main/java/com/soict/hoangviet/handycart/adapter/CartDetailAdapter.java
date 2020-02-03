package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemCartBinding;
import com.soict.hoangviet.handycart.entity.response.ProductListItem;

public class CartDetailAdapter extends EndlessLoadingRecyclerViewAdapter<ItemCartBinding> {

    public CartDetailAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemCartBinding binding, ViewGroup parent) {
        return new CartViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        holder.bind(getItem(position, ProductListItem.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_cart;
    }

    class CartViewHolder extends NormalViewHolder<ItemCartBinding, ProductListItem> {

        public CartViewHolder(ItemCartBinding binding) {
            super(binding);
        }

        @Override
        public void bind(ProductListItem data) {
            binding.setProductListItem(data);
            binding.qscCart.setListener(item -> {

            });
        }
    }
}
