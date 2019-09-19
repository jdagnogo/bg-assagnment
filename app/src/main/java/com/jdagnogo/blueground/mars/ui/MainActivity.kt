package com.jdagnogo.blueground.mars.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jdagnogo.blueground.mars.R
import com.jdagnogo.blueground.mars.modelView.LoginViewModel
import com.jdagnogo.blueground.mars.utils.Result
import com.jdagnogo.blueground.mars.utils.Result.Status.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity() : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
        loginViewModel.currentName.observe(this, emailObserver)

        button.setOnClickListener {
            val email = user_email.text.toString()
            loginViewModel.verifyEmail(email,this)
        }
    }

    val emailObserver = Observer<Result<String>> { result ->
        when (result.status) {
            SUCCESS -> {
                    val email = user_email.text.toString()
                    val password = password.text.toString()
                    loginViewModel.login(email,password)
            }
            LOADING -> return@Observer
            ERROR -> Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
        }
    }
}
