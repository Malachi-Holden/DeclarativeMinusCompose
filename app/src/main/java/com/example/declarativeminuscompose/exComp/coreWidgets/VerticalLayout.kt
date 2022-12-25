package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.LinearLayout
import com.example.declarativeminuscompose.exComp.ExComp

fun ExComp.VerticalLayout(content: ExComp.()->Unit){
    Layout(
        { LinearLayout(this).apply { orientation = LinearLayout.VERTICAL } },
        content
    )
}