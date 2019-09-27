package com.jdagnogo.blueground.mars.modelView

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.data.repository.LoginRepository
import com.jdagnogo.blueground.mars.data.repository.TokenRepository
import com.jdagnogo.blueground.mars.utils.Result
import com.jdagnogo.blueground.mars.utils.Result.Status
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@PrepareForTest(LoginRepository::class, TokenRepository::class, Result::class, Result.Status::class, LoginViewModel::class)
@RunWith(PowerMockRunner::class)
class UnitDetailsViewModelTest {
    companion object {
        const val FAKE_STRING = "FAKE_STRING"
    }

    protected inline fun <reified T> any(): T = Mockito.any(T::class.java)

    protected fun <T> anyOrNull(): T = Mockito.any()

    protected inline fun <reified T> eq(value: T): T = ArgumentMatchers.eq(value)

    private lateinit var sut: LoginViewModel

    private lateinit var loginRepository: LoginRepository

    private lateinit var tokenRepository: TokenRepository

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var currentName: MutableLiveData<Result<String>>

    @Before
    fun setUp() {
        loginRepository = PowerMockito.mock((LoginRepository::class.java))
        tokenRepository = PowerMockito.mock((TokenRepository::class.java))
        sut = spy(LoginViewModel(loginRepository, tokenRepository))
    }

    //region isEmailEmpty
    @Test
    fun testIsEmailEmpty_emailEmpty_true() {
        val email = ""
        val status = Status.ERROR
        given(context.getString(R.string.error_empty_email)).willReturn(FAKE_STRING)
        willDoNothing().given(sut).sendValue(status, FAKE_STRING)
        given(sut.currentName).willReturn(currentName)
        given(currentName.value).willReturn(Result(status,"",""))
        val result = sut.isEmailEmpty(email, context)

        assertThat(result, equalTo(true))
    }

    @Test
    fun testIsEmailEmpty_emailNotEmpty_false() {
        given(context.getString(R.string.error_empty_email)).willReturn(FAKE_STRING)

        val result = sut.isEmailEmpty(FAKE_STRING, context)

        assertThat(result, equalTo(false))
    }
    //end region
}
