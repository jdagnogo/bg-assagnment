package com.jdagnogo.blueground.mars.ui

import android.os.Bundle
import android.util.Log
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
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

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
            loginViewModel.verifyEmail(email)
        }
    }

    val emailObserver = Observer<Result<String>> { result ->
        when (result.status) {
            SUCCESS -> loginViewModel.login()
            LOADING -> Log.d("TOTO", result.message)
            ERROR -> Log.d("TOTO", result.message)
        }
    }
}
