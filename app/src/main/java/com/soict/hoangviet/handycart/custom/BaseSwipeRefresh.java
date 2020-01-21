package com.soict.hoangviet.handycart.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.soict.hoangviet.handycart.R;

public class BaseSwipeRefresh extends SwipeRefreshLayout {

    public BaseSwipeRefresh(@NonNull Context context) {
        super(context);
        initView();
    }

    public BaseSwipeRefresh(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        initColorRefreshing();
    }

    private void initColorRefreshing() {
        setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }

    public void showRefreshing(){
        setRefreshing(true);
    }

    public void hideRefreshing(){
        setRefreshing(false);
    }
}
