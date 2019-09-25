package com.jdagnogo.blueground.mars.di.modules

import com.elifox.legocatalog.api.BaseDataSource
import com.elifox.legocatalog.di.BluegroundAPI
import com.elifox.legocatalog.di.CoroutineScropeIO
import com.jdagnogo.blueground.mars.api.LoginDao
import com.jdagnogo.blueground.mars.api.UnitsDao
import com.jdagnogo.blueground.mars.data.remotedata.UnitsDataFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideBaseDataSource(): BaseDataSource {
        return BaseDataSource()
    }


    @Singleton
    @Provides
    fun provideUnitsDataFactory(
        service: UnitsDao,
        @CoroutineScropeIO ioCoroutineScope: CoroutineScope, baseDataSource: BaseDataSource
    ): UnitsDataFactory {
        return UnitsDataFactory(service,ioCoroutineScope,baseDataSource)
    }

    @Singleton
    @Provides
    fun provideUnitsDao(
        @BluegroundAPI okhttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) =
        createRetrofit(okhttpClient, gsonConverterFactory).create(UnitsDao::class.java)

    @Singleton
    @Provides
    fun provideLoginDao(
        @BluegroundAPI okhttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) =
        createRetrofit(okhttpClient, gsonConverterFactory).create(LoginDao::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LoginDao.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}