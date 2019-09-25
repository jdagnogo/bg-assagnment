package com.jdagnogo.blueground.mars.api

import com.elifox.legocatalog.di.CoroutineScropeIO
import com.google.gson.Gson
import com.jdagnogo.blueground.mars.api.LoginDao.Companion.AUTHORIZATION
import com.jdagnogo.blueground.mars.model.LoginToken
import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class AuthAuthenticator @Inject constructor(
    val tokenRepository: TokenRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val request: Request? = response.request
        ioCoroutineScope.launch(Dispatchers.Unconfined) {
            val retrofitResponse = refreshTokenFromServer()
            if (retrofitResponse.isSuccessful) {
                val body = retrofitResponse.body()
                val accessToken = body?.accessToken
                if (accessToken == null) return@launch
                var loginToken =
                    LoginToken(
                        body.tokenType,
                        body.accessToken,
                        body.refreshToken,
                        body.expiresIn
                    )
                tokenRepository.saveTokenInformations(loginToken)
                relaunchRequest(request, accessToken)
            }
        }
        return request
    }

    // Shame on me, I had an depedency Cycle with dagger. I spent too much time on it
    // If I finish the project I will come back to find a better solution
    suspend fun refreshTokenFromServer(): retrofit2.Response<LoginToken> {
        val retrofit = Retrofit.Builder()
            .baseUrl(LoginDao.ENDPOINT)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        val service = retrofit.create(LoginDao::class.java)
        return service.refreshToken(tokenRepository.getRefreshTokenParameters())
    }

    fun relaunchRequest(request: Request?, accessToken: String): Request? {
        return request
            ?.newBuilder()
            ?.header(AUTHORIZATION, accessToken)
            ?.build()
    }
}