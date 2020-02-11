package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemProductFavoriteBinding;
import com.beetech.ec.tienichmuasam.entity.response.ProductFavoriteResponse;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteProductListener;

public class ProductFavoriteAdapter extends EndlessLoadingRecyclerViewAdapter<ItemProductFavoriteBinding> {
    private FavoriteProductListener listener;

    public ProductFavoriteAdapter(Context context, FavoriteProductListener listener, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
        this.listener = listener;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemProductFavoriteBinding binding, ViewGroup parent) {
        return new ProductFavoriteViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ProductFavoriteViewHolder mProductFavoriteViewHolder = (ProductFavoriteViewHolder) holder;
        mProductFavoriteViewHolder.bind(getItem(position, ProductFavoriteResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_product_favorite;
    }

    public class ProductFavoriteViewHolder extends NormalViewHolder<ItemProductFavoriteBinding, ProductFavoriteResponse> {
        public ProductFavoriteViewHolder(ItemProductFavoriteBinding binding) {
            super(binding);
        }
        @Override
        public void bind(ProductFavoriteResponse data) {
            binding.setHomeProductResponse(data);
            binding.imvFavorite.setOnClickListener(view -> {
                listener.onFavoriteClick(getAdapterPosition());
            });
        }
    }
}
