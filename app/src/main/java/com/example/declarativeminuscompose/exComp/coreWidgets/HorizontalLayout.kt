package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.LinearLayout
import com.example.declarativeminuscompose.exComp.ExComp

fun ExComp.HorizontalLayout(content: ExComp.()->Unit){
    Layout(
        { LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL } },
        content
    )
}