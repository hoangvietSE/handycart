package com.beetech.ec.tienichmuasam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import androidx.databinding.DataBindingUtil;

import com.beetech.ec.tienichmuasam.R;
import com.beetech.ec.tienichmuasam.databinding.ItemChildCategoryBinding;
import com.beetech.ec.tienichmuasam.databinding.ItemParentCategoryBinding;
import com.beetech.ec.tienichmuasam.entity.response.CategoryResponse;

import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CategoryResponse> mListCategory;
    private ItemParentCategoryBinding itemParentCategoryBinding;
    private ItemChildCategoryBinding itemChildCategoryBinding;

    public CategoryAdapter(Context context, List<CategoryResponse> mListCategory) {
        this.context = context;
        this.mListCategory = mListCategory;
    }

    @Override
    public int getGroupCount() {
        return mListCategory.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return mListCategory.get(position).getSubCategories().size();
    }

    @Override
    public Object getGroup(int position) {
        return mListCategory.get(position);
    }

    @Override
    public Object getChild(int parentPosition, int childPosition) {
        return mListCategory.get(parentPosition).getSubCategories().get(childPosition);
    }

    @Override
    public long getGroupId(int groupId) {
        return Long.valueOf(groupId);
    }

    @Override
    public long getChildId(int parentId, int childId) {
        return Long.valueOf(childId);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int position, boolean isExpanded, View view, ViewGroup viewGroup) {
        if (view == null) {
            itemParentCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_parent_category, viewGroup, false);
            view = itemParentCategoryBinding.getRoot();
        } else {
            itemParentCategoryBinding = (ItemParentCategoryBinding) view.getTag();
        }
        itemParentCategoryBinding.setCategoryResponse(mListCategory.get(position));
        itemParentCategoryBinding.setIsExpand(isExpanded);
        view.setTag(itemParentCategoryBinding);
        return view;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        if (view == null) {
            itemChildCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_child_category,
                    viewGroup, false);
            view = itemChildCategoryBinding.getRoot();
        } else {
            itemChildCategoryBinding = (ItemChildCategoryBinding) view.getTag();
        }
        itemChildCategoryBinding.setSubCategoriesItem(mListCategory.get(parentPosition).getSubCategories().get(childPosition));
        view.setTag(itemChildCategoryBinding);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
