package com.soict.hoangviet.handycart.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.soict.hoangviet.handycart.di.ViewModelFactory;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteViewModel;
import com.soict.hoangviet.handycart.ui.favorite.product.ProductFavoriteViewModel;
import com.soict.hoangviet.handycart.ui.favorite.supplier.SupplierFavoriteViewModel;
import com.soict.hoangviet.handycart.ui.home.HomeViewModel;
import com.soict.hoangviet.handycart.ui.login.LoginViewModel;
import com.soict.hoangviet.handycart.ui.main.MainViewModel;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;
import com.soict.hoangviet.handycart.ui.master.MasterViewModel;
import com.soict.hoangviet.handycart.ui.notification.NotificationViewModel;
import com.soict.hoangviet.handycart.ui.profile.ProfileViewModel;
import com.soict.hoangviet.handycart.ui.search.SearchViewModel;
import com.soict.hoangviet.handycart.ui.splash.SplashViewModel;

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
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
