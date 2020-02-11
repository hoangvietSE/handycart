package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemMenuSupplierBinding;
import com.beetech.ec.tienichmuasam.entity.response.ItemMenuDetailSupplierResponse;

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
        holder.bind(getItem(position, ItemMenuDetailSupplierResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_menu_supplier;
    }

    public class MenuViewHolder extends NormalViewHolder<ItemMenuSupplierBinding, ItemMenuDetailSupplierResponse> {

        public MenuViewHolder(ItemMenuSupplierBinding binding) {
            super(binding);
        }

        @Override
        public void bind(ItemMenuDetailSupplierResponse data) {
            binding.setItemMenuDetailSupplierResponse(data);
        }
    }
}
