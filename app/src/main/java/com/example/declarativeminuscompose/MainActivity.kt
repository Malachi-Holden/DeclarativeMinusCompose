package com.example.declarativeminuscompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.declarativeminuscompose.exComp.*
import com.example.declarativeminuscompose.exComp.coreWidgets.Button
import com.example.declarativeminuscompose.exComp.coreWidgets.VerticalLayout
import com.example.declarativeminuscompose.exComp.coreWidgets.Text

class MainActivity : ExCompActivity() {
    val x = MutableLiveData(8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VerticalLayout {
                x.bind()
                Text("value: ${x.value}")
                x.value = 9
//                var counter = addState(5) // very bad! causes infinite redrawing!!!!
//                Text("The button: ${counter.value}")
//                Button(
//                    onClick = {
//                        counter.value = 6
//                },
//                    "Yes, this is the button")
            }

        }
    }
}
