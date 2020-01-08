package com.soict.hoangviet.handycart.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.soict.hoangviet.handycart.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseBottomBar extends CustomViewConstraintLayout {
    public BaseBottomBar(Context context) {
        super(context);
    }

    public BaseBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private TextView homeTab;
    private TextView favoriteTab;
    private TextView searchTab;
    private TextView notificationTab;
    private TextView profileTab;
    private OnBottomBarClickListener listener;

    private ArrayList<Integer> mTabIconSelected;

    private ArrayList<Integer> mTabIconDisselected;

    private ArrayList<TextView> mTabs;

    @Override
    int getLayoutRes() {
        return R.layout.layout_bottom_bar;
    }

    @Override
    int[] getStyableRes() {
        return null;
    }

    @Override
    void initView() {
        homeTab = view.findViewById(R.id.home_tab);
        favoriteTab = view.findViewById(R.id.favorite_tab);
        searchTab = view.findViewById(R.id.search_tab);
        notificationTab = view.findViewById(R.id.notification_tab);
        profileTab = view.findViewById(R.id.profile_tab);
        mTabs = new ArrayList<>(Arrays.asList(
                homeTab,
                favoriteTab,
                searchTab,
                notificationTab,
                profileTab));
        mTabIconSelected = new ArrayList<>(Arrays.asList(
                R.drawable.ic_tab_home_select,
                R.drawable.ic_tab_favourite_select,
                R.drawable.ic_tab_search_select,
                R.drawable.ic_tab_notification_select,
                R.drawable.ic_tab_profile_select));
        mTabIconDisselected = new ArrayList<>(Arrays.asList(
                R.drawable.ic_tab_home_disselect,
                R.drawable.ic_tab_favourite_disselect,
                R.drawable.ic_tab_search_disselect_,
                R.drawable.ic_tab_notification_disselect,
                R.drawable.ic_tab_profile_disselect));
        for(int index = 0; index < mTabs.size(); index++){
            int finalIndex = index;
            mTabs.get(index).setOnClickListener(view -> {
                listener.onBottomBarClick(finalIndex);
                onTabClick(finalIndex);
            });
        }
        onTabClick(0);
    }

    private void onTabClick(int position) {
        for (int index = 0; index < mTabs.size(); index++) {
            if (index == position) {
                mTabs.get(index).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                mTabs.get(index).setCompoundDrawablesWithIntrinsicBounds(0, mTabIconSelected.get(index), 0, 0);
            } else {
                mTabs.get(index).setTextColor(ContextCompat.getColor(getContext(), R.color.color_bottom_tab_line));
                mTabs.get(index).setCompoundDrawablesWithIntrinsicBounds(0, mTabIconDisselected.get(index), 0, 0);
            }
        }
    }

    public void setOnBottomBarClickListener(OnBottomBarClickListener listener) {
        this.listener = listener;
    }

    @Override
    void initListener() {
    }

    @Override
    void initData() {
    }

    @Override
    void initDataFromStyable(TypedArray mTypedArray) {
    }

    public interface OnBottomBarClickListener {
        void onBottomBarClick(int position);
    }
}
