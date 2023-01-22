package com.holden.declarativeminuscompose.exComp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner

class ExCompView(
    lifecycleOwner: LifecycleOwner,
    context: Context,
    content: ExComp.() -> Unit
) :
    FrameLayout(context) {
    val exComp = ExComp.default(lifecycleOwner, ExComp.Comptext())

    init {
        exComp.observe {
            exComp.content()
            removeAllViews()
            addView(exComp.buildView(context))
        }
    }
}