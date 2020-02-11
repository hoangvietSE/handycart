package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemListProductBinding;
import com.beetech.ec.tienichmuasam.entity.response.HomeProductResponse;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteProductListener;

public class ListProductAdapter extends EndlessLoadingRecyclerViewAdapter<ItemListProductBinding> {
    private FavoriteProductListener listener;

    public ListProductAdapter(Context context, FavoriteProductListener listener, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
        this.listener = listener;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemListProductBinding binding, ViewGroup parent) {
        return new ListProductViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        holder.bind(getItem(position, HomeProductResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_list_product;
    }

    public class ListProductViewHolder extends NormalViewHolder<ItemListProductBinding, HomeProductResponse> {

        public ListProductViewHolder(ItemListProductBinding binding) {
            super(binding);
        }

        @Override
        public void bind(HomeProductResponse data) {
            binding.setListProductResponse(data);
            binding.itemQuantity.setListener(item -> {
            });
            binding.imvFavorite.setOnClickListener(view -> {
                listener.onFavoriteClick(getAdapterPosition());
            });
            binding.btnAddToCart.setOnClickListener(view -> {
                listener.onCartClick(binding.imvProductCopy, getAdapterPosition(), binding.itemQuantity.getQuantity());
            });
            binding.imvProduct.setOnClickListener(view -> {
                listener.onDetailClick(getAdapterPosition());
            });
        }
    }
}
