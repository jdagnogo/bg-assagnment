package com.jdagnogo.blueground.mars.di.modules


import com.jdagnogo.blueground.mars.ui.BrowseUnitsActivity
import com.jdagnogo.blueground.mars.ui.BrowseUnitsFragment
import com.jdagnogo.blueground.mars.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeBrowseUnitsFragment(): BrowseUnitsFragment

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeBrowseUnitsActivity(): BrowseUnitsActivity
}