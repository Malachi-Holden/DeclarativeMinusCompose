package com.example.declarativeminuscompose

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.declarativeminuscompose.exComp.*
import com.example.declarativeminuscompose.exComp.coreWidgets.Button
import com.example.declarativeminuscompose.exComp.coreWidgets.HorizontalLayout
import com.example.declarativeminuscompose.exComp.coreWidgets.VerticalLayout
import com.example.declarativeminuscompose.exComp.coreWidgets.Text

class MainActivity : ExCompActivity() {
    val x = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            x.bind()
            InnerData(x.value, { x.value = it })

        }
    }
}

fun ExComp.InnerData(x: Int?, onChange: (Int?)->Unit){
    VerticalLayout {
        Text("value: $x", modifier = Modifier().textSize(30f))
        Text("value again: $x", modifier = Modifier().textSize(30f))
        HorizontalLayout {
            Text("the button:", modifier = Modifier().textSize(30f))
            Button(
                modifier = Modifier().textSize(30f),
                onClick = { onChange( x?.plus(1)) },
                text = "increment"
            )
        }

    }
}