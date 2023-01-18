package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.Button
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

fun ExComp.Button(modifier: Modifier = Modifier(), onClick: ()->Unit, text: String)
    = BuildExComp(modifier, { context ->
    Button(context).apply {
        setText(text)
        setOnClickListener { onClick() }
    }
}){}