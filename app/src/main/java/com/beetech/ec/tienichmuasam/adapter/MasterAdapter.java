package com.beetech.ec.tienichmuasam.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteFragment;
import com.beetech.ec.tienichmuasam.ui.home.HomeFragment;
import com.beetech.ec.tienichmuasam.ui.notification.NotificationFragment;
import com.beetech.ec.tienichmuasam.ui.profile.ProfileFragment;
import com.beetech.ec.tienichmuasam.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MasterAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public MasterAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments.add(new HomeFragment());
        fragments.add(new FavoriteFragment());
        fragments.add(new SearchFragment());
        fragments.add(new NotificationFragment());
        fragments.add(new ProfileFragment());
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
