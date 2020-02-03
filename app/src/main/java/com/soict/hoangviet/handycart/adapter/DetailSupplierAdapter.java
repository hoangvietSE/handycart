package com.soict.hoangviet.handycart.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.soict.hoangviet.handycart.ui.detailproduct.description.DescriptionProductFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.guide.GuideProductFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.menu.MenuFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.service.ServiceFragment;

import java.util.ArrayList;

public class DetailSupplierAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public DetailSupplierAdapter(@NonNull FragmentManager fm, Bundle menuBundle, Bundle serviceBundle) {
        super(fm);
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setArguments(menuBundle);
        ServiceFragment serviceFragment = new ServiceFragment();
        serviceFragment.setArguments(serviceBundle);
        fragmentArrayList.add(menuFragment);
        fragmentArrayList.add(serviceFragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
