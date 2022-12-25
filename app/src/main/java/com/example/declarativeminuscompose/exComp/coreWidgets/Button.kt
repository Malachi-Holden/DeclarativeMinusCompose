package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.Button
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

fun ExComp.Button(onClick: ()->Unit, text: String, modifier: Modifier = Modifier()){
    AndroidView(modifier) { Button(this).apply {
        setText(text)
        setOnClickListener { onClick() }
    } }
}