package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemProductFavoriteBinding;
import com.soict.hoangviet.handycart.entity.response.ProductFavoriteResponse;

public class ProductFavoriteAdapter extends EndlessLoadingRecyclerViewAdapter<ItemProductFavoriteBinding> {

    public ProductFavoriteAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
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
        }
    }
}
