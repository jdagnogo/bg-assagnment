package com.jdagnogo.blueground.mars.di.component

import com.jdagnogo.blueground.mars.BluegroundMarsApplication
import com.jdagnogo.blueground.mars.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        DataModule::class,
        RepositoryModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BluegroundMarsApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: BluegroundMarsApplication)
}