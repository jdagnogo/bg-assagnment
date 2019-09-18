package com.elifox.legocatalog.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jdagnogo.blueground.mars.modelView.LoginViewModel
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
    abstract fun bindThemeViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
