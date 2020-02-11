package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemSupplierFavoriteBinding;
import com.beetech.ec.tienichmuasam.entity.response.SupplierFavoriteResponse;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteSupplierListener;

public class SupplierFavoriteAdapter extends EndlessLoadingRecyclerViewAdapter<ItemSupplierFavoriteBinding> {
    private FavoriteSupplierListener listener;

    public SupplierFavoriteAdapter(Context context, FavoriteSupplierListener listener, boolean enableSelectedMode) {
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
                listener.onFavoriteClick(getAdapterPosition());
            });
        }
    }
}
