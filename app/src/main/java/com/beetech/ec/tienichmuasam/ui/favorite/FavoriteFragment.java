package com.beetech.ec.tienichmuasam.ui.favorite;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.adapter.FavoriteAdapter;
import com.beetech.ec.tienichmuasam.base.BaseFragment;
import com.beetech.ec.tienichmuasam.databinding.FragmentFavoriteBinding;

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
