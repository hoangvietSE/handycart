package com.soict.hoangviet.handycart.data.network;

import com.soict.hoangviet.handycart.utils.Define;

public class ApiConstant {
    public static final String BANNER = "banner";
    public static final String PRODUCT_CATEGORY = "product_category";
    public static final String SUPPLIER = "supplier";
    public static final String SEARCH = "search";
    public static final String CATEGORY = "categories";
    public static final String LOGIN = "user_sessions";
    public static final String LOGOUT = "user_sessions/{" + Define.Api.Query.ID + "}";
    public static final String PRODUCT_FAVORITE = "user_product_favorites";
    public static final String SUPPLIER_FAVORITE = "user_restaurent_favorites";
    public static final String SUPPLIER_FAVORITE_DELETE = "user_restaurent_favorites/{" + Define.Api.Query.ID + "}";
    public static final String CART_AMOUNT = "cart/amount";
    public static final String CART = "cart";
}
