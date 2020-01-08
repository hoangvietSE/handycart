package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.databinding.ItemInfiniteBannerBinding;
import com.soict.hoangviet.handycart.entity.DataItem;

import java.util.List;

public class BannerInfiniteAdapter extends LoopingPagerAdapter<DataItem> {
    ItemInfiniteBannerBinding binding;

    public BannerInfiniteAdapter(Context context, List<DataItem> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_infinite_banner, container, false);
        return binding.getRoot();
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        binding.setDataItem(itemList.get(listPosition));
    }
}
