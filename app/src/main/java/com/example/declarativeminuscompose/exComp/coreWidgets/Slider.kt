package com.example.declarativeminuscompose.exComp.coreWidgets

import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier
import com.google.android.material.slider.Slider

fun ExComp.Slider(modifier: Modifier = Modifier(),
                  value: Float,
                  onValueChange: (Float)->Unit)
    = BuildExComp(modifier, { context ->
        Slider(context).apply {
            this.value = value
            addOnChangeListener{ _, newValue, _ ->
                onValueChange(newValue)
            }
        }
}){}
