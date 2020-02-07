package com.soict.hoangviet.handycart.di.module;

import com.soict.hoangviet.handycart.ui.cart.CartFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.description.DescriptionProductFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.guide.GuideProductFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.DetailSupplierFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.menu.MenuFragment;
import com.soict.hoangviet.handycart.ui.detailsupplier.service.ServiceFragment;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteFragment;
import com.soict.hoangviet.handycart.ui.favorite.product.ProductFavoriteFragment;
import com.soict.hoangviet.handycart.ui.favorite.supplier.SupplierFavoriteFragment;
import com.soict.hoangviet.handycart.ui.guide.GuideFragment;
import com.soict.hoangviet.handycart.ui.home.HomeFragment;
import com.soict.hoangviet.handycart.ui.listproduct.ListProductFragment;
import com.soict.hoangviet.handycart.ui.login.LoginFragment;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;
import com.soict.hoangviet.handycart.ui.multilanguage.MultiLanguageFragment;
import com.soict.hoangviet.handycart.ui.notification.NotificationFragment;
import com.soict.hoangviet.handycart.ui.profile.ProfileFragment;
import com.soict.hoangviet.handycart.ui.search.SearchFragment;
import com.soict.hoangviet.handycart.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract MasterFragment bindMasterFragment();

    @ContributesAndroidInjector
    abstract FavoriteFragment bindFavoriteFragment();

    @ContributesAndroidInjector
    abstract SearchFragment bindSearchFragment();

    @ContributesAndroidInjector
    abstract NotificationFragment bindNotificationFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment bindProfileFragment();

    @ContributesAndroidInjector
    abstract ProductFavoriteFragment bindProductFavoriteFragment();

    @ContributesAndroidInjector
    abstract SupplierFavoriteFragment bindSupplierFavoriteFragment();

    @ContributesAndroidInjector
    abstract GuideFragment bindGuideFragment();

    @ContributesAndroidInjector
    abstract DetailProductFragment bindDetailProductFragment();

    @ContributesAndroidInjector
    abstract DescriptionProductFragment bindDescriptionProductFragment();

    @ContributesAndroidInjector
    abstract CartFragment bindCartFragment();

    @ContributesAndroidInjector
    abstract DetailSupplierFragment bindDetailSupplierFragment();

    @ContributesAndroidInjector
    abstract MenuFragment bindMenuFragment();

    @ContributesAndroidInjector
    abstract ServiceFragment bindServiceFragment();

    @ContributesAndroidInjector
    abstract ListProductFragment bindListProductFragment();

    @ContributesAndroidInjector
    abstract MultiLanguageFragment bindMultiLanguageFragment();

    @ContributesAndroidInjector
    abstract GuideProductFragment bindGuideProductFragment();

}
