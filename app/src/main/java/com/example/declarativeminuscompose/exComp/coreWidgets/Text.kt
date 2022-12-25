package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.TextView
import com.example.declarativeminuscompose.exComp.ExComp


fun ExComp.Text(text: String){
    children.add(ExComp(lifecycleOwner) { TextView(this).apply {
        setText(text)
        textSize = 30f
    } })
}