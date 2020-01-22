package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemSupplierFavoriteBinding;
import com.soict.hoangviet.handycart.entity.response.SupplierFavoriteResponse;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteListener;

public class SupplierFavoriteAdapter extends EndlessLoadingRecyclerViewAdapter<ItemSupplierFavoriteBinding> {

    public SupplierFavoriteAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemSupplierFavoriteBinding binding, ViewGroup parent) {
        return new SupplierFavoriteViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        SupplierFavoriteViewHolder mSupplierFavoriteViewHolder = (SupplierFavoriteViewHolder) holder;
        mSupplierFavoriteViewHolder.bind(getItem(position, SupplierFavoriteResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_supplier_favorite;
    }

    public class SupplierFavoriteViewHolder extends NormalViewHolder<ItemSupplierFavoriteBinding, SupplierFavoriteResponse> {
        public SupplierFavoriteViewHolder(ItemSupplierFavoriteBinding binding) {
            super(binding);
        }

        @Override
        public void bind(SupplierFavoriteResponse data) {
            binding.setHomeSupplierResponse(data);
        }
    }
}
