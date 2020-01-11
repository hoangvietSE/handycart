package com.soict.hoangviet.handycart.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.soict.hoangviet.handycart.R;

public class BaseRowMainProfile extends CustomViewRelativeLayout {
    private ImageView mImage;
    private TextView mDetail;
    private View mDivider;
    private ImageView mNavigate;
    public BaseRowMainProfile(Context context) {
        super(context);
    }

    public BaseRowMainProfile(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRowMainProfile(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayoutRes() {
        return R.layout.layout_row_main_profile;
    }

    @Override
    int[] getStyableRes() {
        return R.styleable.BaseRowMainProfile;
    }

    @Override
    void initView() {
        mImage = view.findViewById(R.id.imv_icon);
        mDetail = view.findViewById(R.id.tv_detail);
        mDivider = view.findViewById(R.id.divider);
        mNavigate = view.findViewById(R.id.imv_navigate);
    }

    @Override
    void initListener() {

    }

    @Override
    void initData() {

    }

    @Override
    void initDataFromStyable(TypedArray mTypedArray) {
        Drawable mainImage = mTypedArray.getDrawable(R.styleable.BaseRowMainProfile_rmp_image);
        setImage(mainImage);
        String detail = mTypedArray.getString(R.styleable.BaseRowMainProfile_rmp_detail);
        setDetail(detail);
        boolean enableDivider = mTypedArray.getBoolean(R.styleable.BaseRowMainProfile_rmp_divider, true);
        setDivider(enableDivider);
        boolean enableNavigate = mTypedArray.getBoolean(R.styleable.BaseRowMainProfile_rmp_enable_navigate, true);
        setNavigate(enableNavigate);
    }

    private void setNavigate(boolean enableNavigate) {
        if(enableNavigate) mNavigate.setVisibility(VISIBLE); else mNavigate.setVisibility(GONE);
    }

    private void setDivider(boolean enableDivider) {
        if(enableDivider) mDivider.setVisibility(VISIBLE); else mDivider.setVisibility(GONE);
    }


    public void setDetail(String detail) {
        if(detail!=null)mDetail.setText(detail);
    }


    private void setImage(Drawable mainImage) {
        if(mainImage!=null) mImage.setImageDrawable(mainImage);
    }
}
