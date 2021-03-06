package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemHomeProductBinding;
import com.beetech.ec.tienichmuasam.entity.response.HomeProductResponse;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteProductListener;

public class HomeProductAdapter extends EndlessLoadingRecyclerViewAdapter<ItemHomeProductBinding> {
    private FavoriteProductListener listener;

    public HomeProductAdapter(Context context, FavoriteProductListener listener, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
        this.listener = listener;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemHomeProductBinding binding, ViewGroup parent) {
        return new HomeProductViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        HomeProductViewHolder homeProductViewHolder = (HomeProductViewHolder) holder;
        HomeProductResponse homeProductResponse = getItem(position, HomeProductResponse.class);
        homeProductViewHolder.bind(homeProductResponse);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_product;
    }

    public class HomeProductViewHolder extends NormalViewHolder<ItemHomeProductBinding, HomeProductResponse> {


        public HomeProductViewHolder(ItemHomeProductBinding binding) {
            super(binding);
        }

        @Override
        public void bind(HomeProductResponse data) {
            binding.setHomeProductResponse(data);
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
