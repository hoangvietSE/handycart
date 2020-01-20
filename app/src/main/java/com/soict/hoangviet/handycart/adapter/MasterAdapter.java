package com.soict.hoangviet.handycart.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.soict.hoangviet.handycart.ui.favorite.FavoriteFragment;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.ui.notification.NotificationFragment;
import com.soict.hoangviet.handycart.ui.profile.ProfileFragment;
import com.soict.hoangviet.handycart.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MasterAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public MasterAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
