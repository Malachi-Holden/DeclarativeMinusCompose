package com.example.declarativeminuscompose.exComp

import android.view.View
import android.widget.Button
import android.widget.TextView

class Modifier {
    var next: Modifier? = null
    var onUpdate: ((View) -> Unit)? = null

    fun apply(){
        next?.apply()
    }

    companion object
}

fun Modifier.textSize(size: Float): Modifier{
    return Modifier().also {
        it.onUpdate = { view ->
            when(view){
                is TextView -> {
                    view.textSize = size
                }
                is Button -> {
                    view.textSize = size
                }
            }
        }
        next = it
    }
}