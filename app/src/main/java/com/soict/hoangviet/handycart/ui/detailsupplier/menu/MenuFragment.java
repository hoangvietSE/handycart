package com.soict.hoangviet.handycart.ui.detailsupplier.menu;

import androidx.appcompat.view.menu.MenuAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.MenuProductAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentDetailSupplierMenuBinding;

import java.util.List;

public class MenuFragment extends BaseFragment<FragmentDetailSupplierMenuBinding> {
    public static final String EXTRA_SUPPLIER_ID = "extra_supplier_id";
    private MenuViewModel mViewModel;
    private MenuProductAdapter menuProductAdapter;
    private int supplierId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_supplier_menu;
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
        initViewModel();
        initMenuAdapter();
    }

    private void initMenuAdapter() {
        menuProductAdapter = new MenuProductAdapter(getContext(), false);
        binding.rcvMenuProduct.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvMenuProduct.setOnLoadingMoreListener(() -> {
            mViewModel.setMenuDetail(supplierId);
        });
        binding.rcvMenuProduct.setAdapter(menuProductAdapter);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel.class);
    }

    @Override
    public void initData() {
        getMenuProduct();
    }

    private void getMenuProduct() {
        supplierId = getArguments().getInt(EXTRA_SUPPLIER_ID, -1);
        mViewModel.setMenuDetail(supplierId);
    }

    @Override
    public void initListener() {
        mViewModel.getMenuProduct().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvMenuProduct.enableLoadmore(canLoadmore);
        binding.rcvMenuProduct.addItem(data);
    }
}
