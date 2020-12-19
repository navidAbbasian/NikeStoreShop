package com.sevenlearn.nikestore.feature.main

import android.os.Bundle
import com.sevenlearn.nikestore.R
import com.sevenlearn.nikestore.common.NikeActivity

class MainActivity : NikeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}