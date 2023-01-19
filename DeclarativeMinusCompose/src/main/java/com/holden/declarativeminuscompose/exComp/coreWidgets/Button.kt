package com.holden.declarativeminuscompose.exComp.coreWidgets

import android.widget.Button
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier

fun ExComp.Button(modifier: Modifier = Modifier(), onClick: ()->Unit, text: String)
    = BuildExComp(modifier, { context ->
    Button(context).apply {
        setText(text)
        setOnClickListener { onClick() }
    }
}){}