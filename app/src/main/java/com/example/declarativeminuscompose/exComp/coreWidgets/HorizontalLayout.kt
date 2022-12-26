package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.LinearLayout
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

fun ExComp.HorizontalLayout(modifier: Modifier = Modifier(), content: ExComp.()->Unit){
    BuildExComp(
        modifier,
        { LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL } },
        content
    )
}