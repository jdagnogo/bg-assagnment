package com.jdagnogo.blueground.mars.di.modules


import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    /*
    * The method returns the Okhttp object
    * */
    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }
}