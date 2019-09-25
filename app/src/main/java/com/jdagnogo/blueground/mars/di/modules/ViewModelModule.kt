package com.jdagnogo.blueground.mars.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jdagnogo.blueground.mars.modelView.BrowseUnitsViewModel
import com.jdagnogo.blueground.mars.modelView.LoginViewModel
import com.jdagnogo.blueground.mars.modelView.UnitDetailsViewModel
import com.jdagnogo.enigma.di.utils.ViewModelFactory
import com.jdagnogo.enigma.di.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseUnitsViewModel::class)
    abstract fun bindUnitViewModel(viewModel: BrowseUnitsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnitDetailsViewModel::class)
    abstract fun bindUnitDetailsViewModel(viewModel: UnitDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
