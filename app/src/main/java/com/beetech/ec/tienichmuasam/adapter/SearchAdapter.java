package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.ec.tienichmuasam.databinding.ItemSearchProductBinding;
import com.beetech.ec.tienichmuasam.entity.response.SearchProductResponse;

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
    protected int getLayoutId() { return R.layout.item_search_product; }

    public class SearchViewHolder extends NormalViewHolder<ItemSearchProductBinding, SearchProductResponse> {

        public SearchViewHolder(ItemSearchProductBinding binding) {
            super(binding);
        }

        @Override
        public void bind(SearchProductResponse data) {
            binding.setSearchProductResponse(data);
        }
    }
}
