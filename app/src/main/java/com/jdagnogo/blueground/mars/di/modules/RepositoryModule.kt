package com.jdagnogo.blueground.mars.di.modules

import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.data.remotedata.LoginRemoteDataSouce
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(service: LoginServiceApi)
            = LoginRemoteDataSouce(service)
}