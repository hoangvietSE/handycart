package com.beetech.ec.tienichmuasam.di.module;

import com.beetech.ec.tienichmuasam.ui.cart.CartFragment;
import com.beetech.ec.tienichmuasam.ui.detailproduct.DetailProductFragment;
import com.beetech.ec.tienichmuasam.ui.detailproduct.description.DescriptionProductFragment;
import com.beetech.ec.tienichmuasam.ui.detailproduct.guide.GuideProductFragment;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.DetailSupplierFragment;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.menu.MenuFragment;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.service.ServiceFragment;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteFragment;
import com.beetech.ec.tienichmuasam.ui.favorite.product.ProductFavoriteFragment;
import com.beetech.ec.tienichmuasam.ui.favorite.supplier.SupplierFavoriteFragment;
import com.beetech.ec.tienichmuasam.ui.guide.GuideFragment;
import com.beetech.ec.tienichmuasam.ui.home.HomeFragment;
import com.beetech.ec.tienichmuasam.ui.listproduct.ListProductFragment;
import com.beetech.ec.tienichmuasam.ui.login.LoginFragment;
import com.beetech.ec.tienichmuasam.ui.master.MasterFragment;
import com.beetech.ec.tienichmuasam.ui.multilanguage.MultiLanguageFragment;
import com.beetech.ec.tienichmuasam.ui.notification.NotificationFragment;
import com.beetech.ec.tienichmuasam.ui.profile.ProfileFragment;
import com.beetech.ec.tienichmuasam.ui.search.SearchFragment;
import com.beetech.ec.tienichmuasam.ui.splash.SplashFragment;

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
