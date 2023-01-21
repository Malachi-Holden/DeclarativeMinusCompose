package com.example.declarativeminuscompose

import android.os.Bundle
import com.holden.declarativeminuscompose.exComp.ExCompActivity

class MainActivity : ExCompActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Login()
        }
    }
}