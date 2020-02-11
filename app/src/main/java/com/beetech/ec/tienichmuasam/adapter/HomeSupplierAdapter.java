package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemHomeSupplierBinding;
import com.beetech.ec.tienichmuasam.entity.response.HomeSupplierResponse;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteSupplierListener;

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
            binding.imvFavorite.setOnClickListener(view -> {
                listener.onFavoriteClick(getAdapterPosition());
            });
            binding.imvSupplier.setOnClickListener(view -> {
                listener.onDetailClick(getAdapterPosition());
            });
        }
    }
}
