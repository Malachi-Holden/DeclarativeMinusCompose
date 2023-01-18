package com.holden.declarativeminuscompose.exComp.coreWidgets

import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier
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
