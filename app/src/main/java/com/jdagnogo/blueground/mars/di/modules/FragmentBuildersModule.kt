package com.jdagnogo.blueground.mars.di.modules

import com.jdagnogo.blueground.mars.ui.BrowseUnitsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeBrowseUnitsFragment(): BrowseUnitsFragment
}
