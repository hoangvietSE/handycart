package com.soict.hoangviet.handycart.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.base.EndlessLoadingRecyclerViewAdapter;
import com.soict.hoangviet.handycart.databinding.ItemNotificationBinding;
import com.soict.hoangviet.handycart.entity.response.NotificationResponse;

public class NotificationAdapter extends EndlessLoadingRecyclerViewAdapter<ItemNotificationBinding> {

    public NotificationAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemNotificationBinding binding, ViewGroup parent) {
        return new NotificationViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        holder.bind(getItem(position, NotificationResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_notification;
    }

    public class NotificationViewHolder extends NormalViewHolder<ItemNotificationBinding, NotificationResponse> {

        public NotificationViewHolder(ItemNotificationBinding binding) {
            super(binding);
        }

        @Override
        public void bind(NotificationResponse data) {
            binding.setNotificationResponse(data);
        }
    }
}
