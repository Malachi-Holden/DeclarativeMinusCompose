package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.TextView
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier


fun ExComp.Text(text: String, modifier: Modifier = Modifier()){
    children.add(ExComp(lifecycleOwner) { TextView(this).apply {
        setText(text)
//        textSize = 30f
    } }.apply { this.modifier = modifier })
}