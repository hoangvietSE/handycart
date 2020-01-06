package com.soict.hoangviet.handycart.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class CustomViewConstraintLayout extends ConstraintLayout {
    private int layoutRes = getLayoutRes();
    private int[] styableRes = getStyableRes();
    private AttributeSet attrs;
    protected View view;

    public CustomViewConstraintLayout(Context context) {
        super(context);
        init();
    }

    public CustomViewConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        init();
    }

    public CustomViewConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        init();
    }

    abstract int getLayoutRes();

    abstract int[] getStyableRes();

    private void init() {
        initLayout();
    }

    private void initLayout() {
        view = LayoutInflater.from(getContext()).inflate(layoutRes, this, true);
        TypedArray mTypedArray = getContext().getTheme().obtainStyledAttributes(attrs, styableRes, 0, 0);
        initView();
        initListener();
        initData();
        initDataFromStyable(mTypedArray);
    }

    abstract void initView();

    abstract void initListener();

    abstract void initData();

    abstract void initDataFromStyable(TypedArray mTypedArray);

    public AttributeSet getAttrs() {
        return attrs;
    }

    public void setAttrs(AttributeSet attrs) {
        this.attrs = attrs;
    }
}
