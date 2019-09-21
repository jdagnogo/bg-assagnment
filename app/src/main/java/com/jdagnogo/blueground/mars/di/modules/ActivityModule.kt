package com.jdagnogo.blueground.mars.di.modules


import com.jdagnogo.blueground.mars.ui.BrowseUnitsActivity
import com.jdagnogo.blueground.mars.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeBroseUnitsActivity(): BrowseUnitsActivity
}