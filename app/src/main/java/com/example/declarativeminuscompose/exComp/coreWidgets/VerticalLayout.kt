package com.example.declarativeminuscompose.exComp.coreWidgets

import android.widget.LinearLayout
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

fun ExComp.VerticalLayout(modifier: Modifier = Modifier(), content: ExComp.()->Unit){
    BuildExComp(
        modifier,
        { context->LinearLayout(context).apply { orientation = LinearLayout.VERTICAL } },
        content
    )
}