package com.jdagnogo.blueground.mars.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jdagnogo.blueground.mars.R
import dagger.android.AndroidInjection

class BrowseUnitsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_units)
    }
}
