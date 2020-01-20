package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemHomeSupplierBinding;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;

public class HomeSupplierAdapter extends EndlessLoadingRecyclerViewAdapter<ItemHomeSupplierBinding> {
    public HomeSupplierAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemHomeSupplierBinding binding, ViewGroup parent) {
        return new HomeSupplierViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        HomeSupplierViewHolder homeSupplierViewHolder = (HomeSupplierViewHolder) holder;
        homeSupplierViewHolder.bind(getItem(position, HomeSupplierResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_supplier;
    }

    public class HomeSupplierViewHolder extends NormalViewHolder<ItemHomeSupplierBinding,HomeSupplierResponse> {

        public HomeSupplierViewHolder(ItemHomeSupplierBinding binding) {
            super(binding);
        }

        @Override
        public void bind(HomeSupplierResponse data) {
            binding.setHomeSupplierResponse(data);
        }
    }
}
