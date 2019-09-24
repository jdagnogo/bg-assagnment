package com.jdagnogo.blueground.mars.modelView

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.*
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.api.model.LoginParameters
import com.jdagnogo.blueground.mars.api.model.LoginResponse
import com.jdagnogo.blueground.mars.api.model.LoginToken
import com.jdagnogo.blueground.mars.data.repository.LoginRepository
import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import com.jdagnogo.blueground.mars.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
) :
    ViewModel() {
    companion object {
        private const val AT = "@"
        private const val DOT = "."
        private const val MAX_CHAR_LENGHT = 4
        private const val MIN_CHAR_LENGHT = 1
        private const val COUNTRY_LENGHT = 2
    }

    private fun saveToken(loginToken: LoginToken) {
        tokenRepository.saveTokenInformations(loginToken)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(LoginParameters(email, password))
            when (result.status) {
                Result.Status.SUCCESS -> {
                    val tokenInformations : LoginToken =(result.data as LoginResponse).token
                    saveToken(tokenInformations)
                    login.value = Result(Result.Status.SUCCESS, "", "")
                }
                Result.Status.ERROR -> login.value = Result(Result.Status.ERROR, "", result.message)
            }
        }
    }

    val login: MutableLiveData<Result<String>> by lazy {
        MutableLiveData<Result<String>>()
    }

    val currentName: MutableLiveData<Result<String>> by lazy {
        MutableLiveData<Result<String>>()
    }

    fun verifyEmail(email: String, context: Context) {
        if (isEmailEmpty(email, context)) return
        if (!isEmailContainsADot(email, context)) return
        if (!isEmailContainsAnAt(email, context)) return
        if (!isEmailSizeCorrect(email, context)) return
        if (!isEmailContainsYearOfBirth(email, context)) return
        if (!isEmailContainsCountry(email, context)) return
        currentName.value = Result(Result.Status.SUCCESS, "", "")
    }

    private fun isEmailContainsCountry(email: String, context: Context): Boolean {
        val emailSplitAroundDot = email.split(DOT)
        val country = emailSplitAroundDot[1]
        if (country.length != COUNTRY_LENGHT) {
            currentName.value = Result(
                Result.Status.ERROR, null, context.getString(R.string.error_country)
            )
            return false
        } else return true
    }

    private fun isEmailContainsAnAt(email: String, context: Context): Boolean {
        val emailSplitAroundDot = email.split(AT)
        if (emailSplitAroundDot.size != 2) {
            currentName.value =
                Result(Result.Status.ERROR, null, context.getString(R.string.error_at))
            return false
        } else return true
    }

    private fun isEmailSizeCorrect(email: String, context: Context): Boolean {
        val emailSplitAroundAt = email.split(AT)
        val firstChar = emailSplitAroundAt[0]
        if (firstChar.length !in MIN_CHAR_LENGHT..MAX_CHAR_LENGHT) {
            currentName.value = Result(
                Result.Status.ERROR,
                "",
                context.getString(R.string.error_size)
            )
            return false
        } else return true
    }

    private fun isEmailContainsYearOfBirth(email: String, context: Context): Boolean {
        val year = email.substringAfter(AT).substringBefore(DOT)
        if (year.toIntOrNull() == null || year.length != 4) {
            currentName.value = Result(
                Result.Status.ERROR, null, context.getString(R.string.error_year_of_birth)
            )
            return false
        } else return true
    }

    private fun isEmailContainsADot(email: String, context: Context): Boolean {
        val emailSplitAroundDot = email.split(DOT)
        if (emailSplitAroundDot.size != 2) {
            currentName.value =
                Result(Result.Status.ERROR, null, context.getString(R.string.error_dot))
            return false
        } else return true
    }

    private fun isEmailEmpty(email: String, context: Context): Boolean {
        if (email.isEmpty()) {
            currentName.value = Result(
                Result.Status.ERROR, null, context.getString(R.string.error_empty_email)
            )
            return true
        } else return false
    }
}