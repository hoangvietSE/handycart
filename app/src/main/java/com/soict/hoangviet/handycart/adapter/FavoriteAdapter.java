package com.soict.hoangviet.handycart.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.ui.favorite.product.ProductFavoriteFragment;
import com.soict.hoangviet.handycart.ui.favorite.supplier.SupplierFavoriteFragment;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends FragmentPagerAdapter {
    private final int PRODUCT = 0;
    private final int SUPPLIER = 1;
    private List<Fragment> fragmentList = new ArrayList<>();
    private Context context;

    public FavoriteAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
        fragmentList.add(new ProductFavoriteFragment());
        fragmentList.add(new SupplierFavoriteFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case PRODUCT:
                return context.getString(R.string.favorite_product_title);
            case SUPPLIER:
                return context.getString(R.string.favorite_supplier_title);
            default:
                return "";
        }
    }
}
