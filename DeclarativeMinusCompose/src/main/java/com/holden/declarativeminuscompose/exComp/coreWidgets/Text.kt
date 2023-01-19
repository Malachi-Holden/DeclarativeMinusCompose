package com.holden.declarativeminuscompose.exComp.coreWidgets

import android.widget.TextView
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier


fun ExComp.Text(text: String, modifier: Modifier = Modifier())
    = BuildExComp(modifier, { context ->
        TextView(context).apply { setText(text) }
}){}
