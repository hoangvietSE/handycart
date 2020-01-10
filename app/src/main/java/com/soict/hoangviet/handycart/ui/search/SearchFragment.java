package com.soict.hoangviet.handycart.ui.search;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soict.hoangviet.handycart.R;
import com.soict.hoangviet.handycart.adapter.SearchAdapter;
import com.soict.hoangviet.handycart.base.BaseFragment;
import com.soict.hoangviet.handycart.databinding.FragmentSearchBinding;

import java.util.List;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private SearchViewModel mViewModel;
    private SearchAdapter searchAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
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
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
        binding.setSearchViewModel(mViewModel);
    }

    @Override
    public void initData() {
        initSearchAdapter();
    }

    private void initSearchAdapter() {
        searchAdapter = new SearchAdapter(getContext(), false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcvSearch.setLayoutManager(layoutManager);
        searchAdapter.addOnItemClickListener((adapter, viewHolder, viewType, position) -> {

        });
        searchAdapter.setLoadingMoreListener(() -> {
            searchAdapter.showLoadingItem(true);
            mViewModel.onSearch(false);
        });
        binding.rcvSearch.setAdapter(searchAdapter);
    }

    @Override
    public void initListener() {
        binding.imvSearch.setOnClickListener(view -> {
            mViewModel.onSearch(true);
        });
        mViewModel.getSearch().observe(this, response -> {
            handleLoadMoreResponse(response, response.isRefresh(), response.isCanLoadmore());
        });
        mViewModel.getResultNumber().observe(this, result->{
            binding.tvResult.setText(result);
        });
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        if (isRefresh) {
            searchAdapter.clear();
        }
        searchAdapter.hideLoadingItem();
        searchAdapter.addModels(data, false);
    }
}
