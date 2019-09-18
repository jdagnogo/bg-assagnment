package com.jdagnogo.blueground.mars.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jdagnogo.blueground.mars.api.LoginServiceApi
import com.jdagnogo.blueground.mars.utils.Result
import javax.inject.Inject

class LoginViewModel @Inject constructor(loginServiceApi: LoginServiceApi) : ViewModel() {
    companion object {
        private const val AT = "@"
        private const val DOT = "."
        private const val MAX_CHAR_LENGHT = 4
        private const val MIN_CHAR_LENGHT = 1
        private const val COUNTRY_LENGHT = 2
    }

    fun login() {
    }

    val currentName: MutableLiveData<Result<String>> by lazy {

        MutableLiveData<Result<String>>()
    }

    fun verifyEmail(email: String) {
        if (isEmailEmpty(email)) return
        if (!isEmailContainsADot(email)) return
        if (!isEmailContainsAnAt(email)) return
        if (!isEmailSizeCorrect(email)) return
        if (!isEmailContainsYearOfBirth(email)) return
        if (!isEmailContainsCountry(email)) return
        currentName.postValue(Result(Result.Status.SUCCESS, "", ""))
    }

    fun isEmailContainsCountry(email: String): Boolean {
        val emailSplitAroundDot = email.split(DOT)
        val country = emailSplitAroundDot[1]
        if (country.length != COUNTRY_LENGHT) {
            currentName.value = Result(
                Result.Status.ERROR,
                "",
                "Your email must have a 2 letter country code after the dot"
            )
            return false
        } else return true
    }

    fun isEmailContainsAnAt(email: String): Boolean {
        val emailSplitAroundDot = email.split(AT)
        if (emailSplitAroundDot.size != 2) {
            currentName.value =
                Result(Result.Status.ERROR, "", "Your email must have an (@)at and only one")
            return false
        } else return true
    }

    fun isEmailSizeCorrect(email: String): Boolean {
        val emailSplitAroundAt = email.split(AT)
        val firstChar = emailSplitAroundAt[0]
        if (firstChar.length !in MIN_CHAR_LENGHT..MAX_CHAR_LENGHT) {
            currentName.value = Result(
                Result.Status.ERROR,
                "",
                "Your email must contains 2 to 8 alphanumeric characters"
            )
            return false
        } else return true
    }

    fun isEmailContainsYearOfBirth(email: String): Boolean {
        val year = email.substringAfter(AT).substringBefore(DOT)
        if (year.toIntOrNull() == null || year.length != 4) {
            currentName.value = Result(
                Result.Status.ERROR,
                "",
                "Your email must contains Year of birth in 4 digits before the @(at) and before the .(dot)"
            )
            return false
        } else return true
    }

    fun isEmailContainsADot(email: String): Boolean {
        val emailSplitAroundDot = email.split(DOT)
        if (emailSplitAroundDot.size != 2) {
            currentName.postValue(
                Result(Result.Status.ERROR, "", "Your email must have an (.)dot and only one")
            )
            return false
        } else return true
    }

    fun isEmailEmpty(email: String): Boolean {
        if (email.isEmpty()) {
            currentName.value = Result(Result.Status.ERROR, "", "Enter an Email")
            return true
        } else return false
    }
}