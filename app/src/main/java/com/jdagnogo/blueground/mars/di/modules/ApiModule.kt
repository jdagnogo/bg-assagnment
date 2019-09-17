package com.jdagnogo.blueground.mars.di.modules


import com.elifox.legocatalog.di.BluegroundAPI
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.api.LoginServiceApi.Companion.ENDPOINT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    /*
    * The method returns the Okhttp object
    * */
    @BluegroundAPI
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideLoginService(@BluegroundAPI okhttpClient: OkHttpClient) =
        createRetrofit(okhttpClient).create(LoginServiceApi::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okhttpClient)
            .build()
    }
}