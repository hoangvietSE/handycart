package com.soict.hoangviet.handycart.ui.master;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.MasterAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentMasterBinding;

public class MasterFragment extends BaseFragment<FragmentMasterBinding> {
    private MasterAdapter masterAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master;
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
        initListener();
        initViewPager();
    }

    private void initViewPager() {
        masterAdapter = new MasterAdapter(getChildFragmentManager());
        binding.masterContainer.setAdapter(masterAdapter);
        binding.masterContainer.setOffscreenPageLimit(4);
    }

    private void initListener() {
        binding.bottomBar.setOnBottomBarClickListener(position -> {
            binding.masterContainer.setCurrentItem(position, true);
        });
    }

    @Override
    public void initData() {

    }
}
