package com.holden.declarativeminuscompose.exComp


import android.R
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Modifier {
    var next: Modifier? = null
    var onUpdate: ((View) -> Unit)? = null

    fun define(onUpdate: ((View) -> Unit)) = Modifier().also {
        it.onUpdate = onUpdate
        it.next = this
    }

    companion object
}

fun Modifier.backgroundColor(a: Int, r: Int, g: Int, b: Int) = define { view->
    view.setBackgroundColor(Color.argb(a,r,g,b))
}

fun Modifier.width(width: Int) = define { view->
    view.layoutParams = ViewGroup.LayoutParams(
        width, view.layoutParams?.height ?: view.minimumHeight
    )
}

fun Modifier.height(height: Int) = define { view->
    view.layoutParams = ViewGroup.LayoutParams(
        view.layoutParams?.width ?: view.minimumWidth, height
    )
}

fun Modifier.fillMaxSize() = define { view ->
    view.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
}

fun Modifier.textSize(size: Float) = define { view ->
    when(view){
        is TextView -> {
            view.textSize = size
        }
    }
}
