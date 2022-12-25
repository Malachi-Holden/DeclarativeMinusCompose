package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.Button
import com.example.declarativeminuscompose.exComp.ExComp

fun ExComp.Button(onClick: ()->Unit, text: String){
    AndroidView { Button(this).apply {
        setText(text)
        setOnClickListener { onClick() }
    } }
}