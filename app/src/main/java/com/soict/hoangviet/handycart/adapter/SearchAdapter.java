package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemSearchProductBinding;
import com.soict.hoangviet.handycart.entity.SearchProductResponse;

public class SearchAdapter extends EndlessLoadingRecyclerViewAdapter<ItemSearchProductBinding> {
    public SearchAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemSearchProductBinding binding, ViewGroup parent) {
        return new SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, SearchProductResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search_product;
    }

    public class SearchViewHolder extends NormalViewHolder<SearchProductResponse> {

        public SearchViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }

        @Override
        public void bind(SearchProductResponse data) {
            binding.setSearchProductResponse(data);
        }
    }
}
