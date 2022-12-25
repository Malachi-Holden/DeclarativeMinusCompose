package com.example.declarativeminuscompose.exComp.coreWidgets

import android.content.Context
import android.view.View
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

fun ExComp.AndroidView(modifier: Modifier = Modifier(), factory: Context.()-> View){
    children.add(ExComp(lifecycleOwner, observer, factory).apply { this.modifier = modifier })
}