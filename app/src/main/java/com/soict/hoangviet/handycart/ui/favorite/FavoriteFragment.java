package com.soict.hoangviet.handycart.ui.favorite;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.FavoriteAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding> {
    private FavoriteAdapter mFavoriteAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initAdapter();
    }

    private void initAdapter() {
        mFavoriteAdapter = new FavoriteAdapter(getContext(), getChildFragmentManager());
        binding.favoriteViewpager.setAdapter(mFavoriteAdapter);
        binding.favoriteTab.setupWithViewPager(binding.favoriteViewpager);
    }

    @Override
    public void initListener() {

    }
}
