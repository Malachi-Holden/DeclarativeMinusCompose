package com.example.declarativeminuscompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.ExCompView
import com.holden.declarativeminuscompose.exComp.coreWidgets.Button
import com.holden.declarativeminuscompose.exComp.coreWidgets.Text
import com.holden.declarativeminuscompose.exComp.coreWidgets.VerticalLayout

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
    }
}