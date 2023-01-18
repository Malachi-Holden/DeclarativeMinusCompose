package com.example.declarativeminuscompose

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.holden.declarativeminuscompose.exComp.ExCompActivity
import com.holden.declarativeminuscompose.exComp.coreWidgets.LazyList
import com.holden.declarativeminuscompose.exComp.coreWidgets.Slider
import com.holden.declarativeminuscompose.exComp.coreWidgets.Text
import com.holden.declarativeminuscompose.exComp.coreWidgets.VerticalLayout

class MainActivity : ExCompActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SliderListView()
        }
    }
}

fun com.holden.declarativeminuscompose.exComp.ExComp.SliderListView(){
    val sliderVal = remember(MutableLiveData(0.1f))
    sliderVal.bind()
    VerticalLayout {
        Slider(value = sliderVal.value ?: 0f, onValueChange = { sliderVal.value = it })
        LazyList(orientation= com.holden.declarativeminuscompose.exComp.coreWidgets.Orientation.Vertical){
            repeat(percentile(sliderVal.value)){
                item {
                    Text("row $it")
                }
            }
        }

    }
}

fun percentile(frac: Float?) = ((frac ?: 0f)*100).toInt()