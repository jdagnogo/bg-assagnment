package com.jdagnogo.blueground.mars.di.modules

import android.content.Context
import com.elifox.legocatalog.di.AppContext
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.data.remotedata.LoginRemoteDataSouce
import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(service: LoginServiceApi)
            = LoginRemoteDataSouce(service)

    @Provides
    @Singleton
    fun provideTokenRepository(@AppContext context: Context) = TokenRepository(context)
}