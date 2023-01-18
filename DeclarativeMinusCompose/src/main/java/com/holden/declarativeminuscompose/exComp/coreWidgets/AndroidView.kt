package com.holden.declarativeminuscompose.exComp.coreWidgets

import android.content.Context
import android.view.View
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier

fun ExComp.AndroidView(modifier: Modifier = Modifier(), factory: ExComp.(Context)-> View)
    = BuildExComp(modifier, factory){}