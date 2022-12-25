package com.example.declarativeminuscompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.declarativeminuscompose.exComp.*
import com.example.declarativeminuscompose.exComp.coreWidgets.Button
import com.example.declarativeminuscompose.exComp.coreWidgets.VerticalLayout
import com.example.declarativeminuscompose.exComp.coreWidgets.Text

class MainActivity : ExCompActivity() {
    val x = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VerticalLayout {
                x.bind()
                Text("value: ${x.value}")
                Button(onClick = { x.value = x.value?.plus(1) }, "increment")
            }

        }
    }
}
