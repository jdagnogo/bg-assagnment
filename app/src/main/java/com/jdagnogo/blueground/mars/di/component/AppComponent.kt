package com.jdagnogo.blueground.mars.di.component

import android.app.Application
import com.elifox.legocatalog.di.ViewModelModule
import com.jdagnogo.blueground.mars.BluegroundMarsApplication
import com.jdagnogo.blueground.mars.di.modules.ActivityModule
import com.jdagnogo.blueground.mars.di.modules.ApiModule
import com.jdagnogo.blueground.mars.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
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
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(application: BluegroundMarsApplication)
}