package com.beetech.ec.tienichmuasam.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.beetech.ec.tienichmuasam.ui.detailproduct.description.DescriptionProductFragment;
import com.beetech.ec.tienichmuasam.ui.detailproduct.guide.GuideProductFragment;

import java.util.ArrayList;

public class DetailProductAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public DetailProductAdapter(@NonNull FragmentManager fm, Bundle descriptionBundle, Bundle guideBundle) {
        super(fm);
        DescriptionProductFragment descriptionProductFragment = new DescriptionProductFragment();
        descriptionProductFragment.setArguments(descriptionBundle);
        GuideProductFragment guideProductFragment = new GuideProductFragment();
        guideProductFragment.setArguments(guideBundle);
        fragmentArrayList.add(descriptionProductFragment);
        fragmentArrayList.add(guideProductFragment);
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
