package com.example.declarativeminuscompose.exComp.coreWidgets

import android.content.Context
import android.view.View
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

fun ExComp.AndroidView(modifier: Modifier = Modifier(), factory: ExComp.(Context)-> View)
    = BuildExComp(modifier, factory){}