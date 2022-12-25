package com.example.declarativeminuscompose.exComp.coreWidgets

import android.content.Context
import android.view.View
import com.example.declarativeminuscompose.exComp.ExComp

fun ExComp.AndroidView(factory: Context.()-> View){
    children.add(ExComp(lifecycleOwner, observer, factory))
}