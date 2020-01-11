package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemHomeProductBinding;
import com.soict.hoangviet.handycart.entity.HomeProductResponse;

public class HomeProductAdapter extends EndlessLoadingRecyclerViewAdapter<ItemHomeProductBinding> {

    public HomeProductAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
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
        }
    }
}
