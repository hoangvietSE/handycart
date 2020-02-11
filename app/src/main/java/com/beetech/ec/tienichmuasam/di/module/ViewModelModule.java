package com.beetech.ec.tienichmuasam.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.beetech.ec.tienichmuasam.di.ViewModelFactory;
import com.beetech.ec.tienichmuasam.ui.cart.CartViewModel;
import com.beetech.ec.tienichmuasam.ui.detailproduct.DetailProductViewModel;
import com.beetech.ec.tienichmuasam.ui.detailproduct.description.DescriptionProductViewModel;
import com.beetech.ec.tienichmuasam.ui.detailproduct.guide.GuideProductViewModel;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.DetailSupplierViewModel;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.menu.MenuViewModel;
import com.beetech.ec.tienichmuasam.ui.detailsupplier.service.ServiceViewModel;
import com.beetech.ec.tienichmuasam.ui.favorite.FavoriteViewModel;
import com.beetech.ec.tienichmuasam.ui.favorite.product.ProductFavoriteViewModel;
import com.beetech.ec.tienichmuasam.ui.favorite.supplier.SupplierFavoriteViewModel;
import com.beetech.ec.tienichmuasam.ui.home.HomeViewModel;
import com.beetech.ec.tienichmuasam.ui.listproduct.ListProductViewModel;
import com.beetech.ec.tienichmuasam.ui.login.LoginViewModel;
import com.beetech.ec.tienichmuasam.ui.main.MainViewModel;
import com.beetech.ec.tienichmuasam.ui.master.MasterViewModel;
import com.beetech.ec.tienichmuasam.ui.multilanguage.MultiLanguageViewModel;
import com.beetech.ec.tienichmuasam.ui.notification.NotificationViewModel;
import com.beetech.ec.tienichmuasam.ui.profile.ProfileViewModel;
import com.beetech.ec.tienichmuasam.ui.search.SearchViewModel;
import com.beetech.ec.tienichmuasam.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    //bind ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MasterViewModel.class)
    abstract ViewModel bindMasterViewModel(MasterViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel.class)
    abstract ViewModel bindFavoriteViewModel(FavoriteViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindSearchViewModel(SearchViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel bindNotificationViewModel(NotificationViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductFavoriteViewModel.class)
    abstract ViewModel bindProductFavoriteViewModel(ProductFavoriteViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SupplierFavoriteViewModel.class)
    abstract ViewModel bindSupplierFavoriteViewModel(SupplierFavoriteViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailProductViewModel.class)
    abstract ViewModel bindDetailProductViewModel(DetailProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DescriptionProductViewModel.class)
    abstract ViewModel bindDescriptionProductViewModel(DescriptionProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(GuideProductViewModel.class)
    abstract ViewModel bindGuideProductViewModel(GuideProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindCartViewModel(CartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailSupplierViewModel.class)
    abstract ViewModel bindDetailSupplierViewModel(DetailSupplierViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel.class)
    abstract ViewModel bindMenuViewModel(MenuViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ServiceViewModel.class)
    abstract ViewModel bindServiceViewModel(ServiceViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListProductViewModel.class)
    abstract ViewModel bindListProductViewModel(ListProductViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MultiLanguageViewModel.class)
    abstract ViewModel bindMultiLanguageViewModel(MultiLanguageViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
