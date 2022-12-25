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
            x.bind()
            VerticalLayout {
                InnerData(x)
            }

        }
    }
}

fun ExComp.InnerData(x: MutableLiveData<Int>){
    Text("value: ${x.value}", modifier = Modifier().textSize(30f))
    Button(
        modifier = Modifier().textSize(30f),
        onClick = { x.value = x.value?.plus(1) },
        text = "increment"
    )
}