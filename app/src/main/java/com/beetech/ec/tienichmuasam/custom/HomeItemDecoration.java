package com.beetech.ec.tienichmuasam.custom;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeItemDecoration extends BaseDecoration {
    public HomeItemDecoration(float padding) {
        super(padding);
    }

    public HomeItemDecoration(float paddingTop, float paddingStart) {
        super(paddingTop, paddingStart);
    }

    public HomeItemDecoration(float paddingTop, float paddingStart, float paddingEnd, float paddingBottom) {
        super(paddingTop, paddingStart, paddingEnd, paddingBottom);
    }

    @Override
    void setupOutRect(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        outRect.left = getPaddingStart();
    }

}
