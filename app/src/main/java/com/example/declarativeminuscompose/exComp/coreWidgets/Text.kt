package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.TextView
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier


fun ExComp.Text(text: String, modifier: Modifier = Modifier())
    = BuildExComp(modifier, {
        TextView(this).apply { setText(text) }
}){}
