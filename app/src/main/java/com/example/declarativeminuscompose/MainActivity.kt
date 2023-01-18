package com.example.declarativeminuscompose

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.example.declarativeminuscompose.exComp.*
import com.example.declarativeminuscompose.exComp.coreWidgets.*

class MainActivity : ExCompActivity() {
    val sliderVal = MutableLiveData(0.1f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SliderListView()
        }
    }
}

fun ExComp.SliderListView(){
    val sliderVal = remember(MutableLiveData(0.1f))
    sliderVal.bind()
    VerticalLayout {
        Slider(value = sliderVal.value ?: 0f, onValueChange = { sliderVal.value = it })
        LazyList(orientation= Orientation.Vertical){
            repeat(percentile(sliderVal.value)){
                item {
                    Text("row $it")
                }
            }
        }

    }
}

fun percentile(frac: Float?) = ((frac ?: 0f)*100).toInt()