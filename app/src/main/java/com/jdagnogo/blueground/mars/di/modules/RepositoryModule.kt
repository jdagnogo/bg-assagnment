package com.jdagnogo.blueground.mars.di.modules

import android.content.Context
import com.elifox.legocatalog.api.BaseDataSource
import com.elifox.legocatalog.di.AppContext
import com.elifox.legocatalog.di.CoroutineScropeIO
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.api.UnitsDao
import com.jdagnogo.blueground.mars.data.remotedata.LoginRemoteDataSouce
import com.jdagnogo.blueground.mars.data.remotedata.UnitsRemoteDataSource
import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(service: LoginServiceApi, baseDataSource: BaseDataSource) =
        LoginRemoteDataSouce(service, baseDataSource)

    @Singleton
    @Provides
    fun provideUnitRemoteDataSource(
        service: UnitsDao, @CoroutineScropeIO ioCoroutineScope: CoroutineScope,
        baseDataSource: BaseDataSource
    ) = UnitsRemoteDataSource(service, ioCoroutineScope, baseDataSource)

    @Provides
    @Singleton
    fun provideTokenRepository(@AppContext context: Context) = TokenRepository(context)
}