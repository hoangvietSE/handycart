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
    private FavoriteListener listener;

    public SupplierFavoriteAdapter(Context context, FavoriteListener listener, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
        this.listener = listener;
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
            binding.imvFavorite.setOnClickListener(view -> {
                listener.onClick(getAdapterPosition());
            });
        }
    }
}
