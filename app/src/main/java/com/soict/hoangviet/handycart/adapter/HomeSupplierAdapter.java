package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemHomeSupplierBinding;
import com.soict.hoangviet.handycart.entity.response.HomeSupplierResponse;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteProductListener;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteSupplierListener;

public class HomeSupplierAdapter extends EndlessLoadingRecyclerViewAdapter<ItemHomeSupplierBinding> {
    private FavoriteSupplierListener listener;

    public HomeSupplierAdapter(Context context, FavoriteSupplierListener listener, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
        this.listener = listener;
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

    public class HomeSupplierViewHolder extends NormalViewHolder<ItemHomeSupplierBinding, HomeSupplierResponse> {

        public HomeSupplierViewHolder(ItemHomeSupplierBinding binding) {
            super(binding);
        }

        @Override
        public void bind(HomeSupplierResponse data) {
            binding.setHomeSupplierResponse(data);
            binding.imvFavorite.setOnClickListener(view->{
                listener.onFavoriteClick(getAdapterPosition());
            });
        }
    }
}
