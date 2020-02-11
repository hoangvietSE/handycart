package com.beetech.ec.tienichmuasam.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class LockedViewPager extends ViewPager {
    public LockedViewPager(@NonNull Context context) {
        super(context);
    }

    public LockedViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Never allow swiping to switch between pages
        return false;
    }
}
