package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemMenuSupplierBinding;
import com.soict.hoangviet.handycart.entity.response.ItemMenuDetailSupplierResponse;

public class MenuProductAdapter extends EndlessLoadingRecyclerViewAdapter<ItemMenuSupplierBinding> {

    public MenuProductAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemMenuSupplierBinding binding, ViewGroup parent) {
        return new MenuViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_menu_supplier;
    }

    public class MenuViewHolder extends NormalViewHolder<ItemMenuSupplierBinding, ItemMenuDetailSupplierResponse>{

        public MenuViewHolder(ItemMenuSupplierBinding binding) {
            super(binding);
        }

        @Override
        public void bind(ItemMenuDetailSupplierResponse data) {

        }
    }
}
