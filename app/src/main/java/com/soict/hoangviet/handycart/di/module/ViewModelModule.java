package com.soict.hoangviet.handycart.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.soict.hoangviet.handycart.di.ViewModelFactory;
import com.soict.hoangviet.handycart.ui.cart.CartViewModel;
import com.soict.hoangviet.handycart.ui.detailproduct.DetailProductViewModel;
import com.soict.hoangviet.handycart.ui.detailproduct.description.DescriptionProductViewModel;
import com.soict.hoangviet.handycart.ui.detailproduct.guide.GuideProductFragment;
import com.soict.hoangviet.handycart.ui.detailproduct.guide.GuideProductViewModel;
import com.soict.hoangviet.handycart.ui.detailsupplier.DetailSupplierViewModel;
import com.soict.hoangviet.handycart.ui.detailsupplier.menu.MenuViewModel;
import com.soict.hoangviet.handycart.ui.detailsupplier.service.ServiceViewModel;
import com.soict.hoangviet.handycart.ui.favorite.FavoriteViewModel;
import com.soict.hoangviet.handycart.ui.favorite.product.ProductFavoriteViewModel;
import com.soict.hoangviet.handycart.ui.favorite.supplier.SupplierFavoriteViewModel;
import com.soict.hoangviet.handycart.ui.guide.GuideViewModel;
import com.soict.hoangviet.handycart.ui.home.HomeViewModel;
import com.soict.hoangviet.handycart.ui.listproduct.ListProductViewModel;
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
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
