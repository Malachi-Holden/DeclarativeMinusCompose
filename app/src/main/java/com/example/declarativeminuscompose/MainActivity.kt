package com.example.declarativeminuscompose

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.example.declarativeminuscompose.exComp.*
import com.example.declarativeminuscompose.exComp.coreWidgets.*

class MainActivity : ExCompActivity() {
    val x = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            x.bind()
            VerticalLayout {
                Button({x.value?.let { x.value = it + 1 }}, "increment",
                    modifier = Modifier()
                    .width(1000)
//                    .textSize(70f)
                    .backgroundColor(255,255,0,0)
                )
                LazyList(Orientation.Vertical){
                    repeat(200){
                        item {
                            Text("row $it")
                        }
                    }
                }
            }
        }
    }
}