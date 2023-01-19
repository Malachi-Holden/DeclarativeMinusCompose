package com.holden.declarativeminuscompose.exComp.coreWidgets

import android.widget.LinearLayout
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier

fun ExComp.HorizontalLayout(modifier: Modifier = Modifier(), content: ExComp.()->Unit){
    BuildExComp(
        modifier,
        { context-> LinearLayout(context).apply { orientation = LinearLayout.HORIZONTAL } },
        content
    )
}