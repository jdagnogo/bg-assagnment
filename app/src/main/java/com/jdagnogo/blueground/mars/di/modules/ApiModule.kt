package com.jdagnogo.blueground.mars.di.modules


import android.content.Context
import com.elifox.legocatalog.di.AppContext
import com.elifox.legocatalog.di.BluegroundAPI
import com.elifox.legocatalog.di.CoroutineScropeIO
import com.google.gson.Gson
import com.jdagnogo.blueground.mars.BluegroundMarsApplication
import com.jdagnogo.blueground.mars.api.AuthInterceptor
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.api.LoginServiceApi.Companion.ENDPOINT
import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    /*
    * The method returns the Okhttp object
    * */
    @BluegroundAPI
    @Provides
    @Singleton
    fun provideOkhttpClient(
        tokenRepository: TokenRepository,
        @CoroutineScropeIO ioCoroutineScope: CoroutineScope
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.authenticator(AuthInterceptor(tokenRepository, ioCoroutineScope))
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideTokenRepository(@AppContext context: Context) = TokenRepository(context)

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideLoginService(
        @BluegroundAPI okhttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) =
        createRetrofit(okhttpClient, gsonConverterFactory).create(LoginServiceApi::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @AppContext
    @Provides
    @Singleton
    fun provideContext(application: BluegroundMarsApplication): Context = application
}