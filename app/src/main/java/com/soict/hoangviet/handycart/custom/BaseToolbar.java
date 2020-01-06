package com.soict.hoangviet.handycart.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soict.hoangviet.handycart.R;

public class BaseToolbar extends CustomViewConstraintLayout {

    private static final int ICON_LEFT_NONE = 0;
    private static final int ICON_LEFT_MENU = 1;
    private static final int ICON_LEFT_BACK = 2;
    private TextView tvTitle;
    private ImageView leftButton;

    public BaseToolbar(Context context) {
        super(context);
    }

    public BaseToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayoutRes() {
        return R.layout.layout_toolbar;
    }

    @Override
    int[] getStyableRes() {
        return R.styleable.BaseToolbar;
    }

    @Override
    void initView() {
        tvTitle = view.findViewById(R.id.tv_title);
        leftButton = view.findViewById(R.id.imv_left);
    }

    @Override
    void initListener() {

    }

    @Override
    void initData() {

    }

    @Override
    void initDataFromStyable(TypedArray mTypedArray) {
        String title = mTypedArray.getString(R.styleable.BaseToolbar_title);
        int iconLeft = mTypedArray.getInt(R.styleable.BaseToolbar_icon_left, 0);
        setToolbarTitle(title);
        setIconLeft(iconLeft);
    }

    public void setIconLeft(int iconLeft) {
        switch (iconLeft) {
            case ICON_LEFT_NONE:
                leftButton.setVisibility(View.GONE);
                break;
            case ICON_LEFT_MENU:
                leftButton.setImageDrawable(getContext().getDrawable(R.drawable.ic_menu));
                break;
            case ICON_LEFT_BACK:
                leftButton.setImageDrawable(getContext().getDrawable(R.drawable.ic_back));
                break;
        }
    }

    public void setToolbarTitle(String title) {
        if (title != null) tvTitle.setText(title);
    }

}
