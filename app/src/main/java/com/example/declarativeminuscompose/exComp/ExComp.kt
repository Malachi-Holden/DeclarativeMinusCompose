package com.example.declarativeminuscompose.exComp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class ExComp(val lifecycleOwner: LifecycleOwner, observer: Observer? = null, val factory: Context.() -> View) {
    val children = mutableListOf<ExComp>()
    val listeners = mutableSetOf<LiveData<*>>()

    var observer = observer
        set(value) {
            field = value
            field?.observe(this)
        }

    fun <T>LiveData<T>.bind(){
        if (observer?.listeners?.contains(this) == true) return
        observer?.listeners?.add(this)
        observe(lifecycleOwner){
            value-> observer?.observe(this@ExComp)
        }
    }

    fun Layout(root: Context.() -> ViewGroup, content: ExComp.() -> Unit){
        children.add(
            ExComp(lifecycleOwner, observer, root).apply(content)
        )
    }

    fun buildView(context: Context): View = context.factory().apply {
        if (this is ViewGroup) children.forEach { child -> addView(child.buildView(context)) }
    }

    companion object {
        fun default(lifecycleOwner: LifecycleOwner) = ExComp(lifecycleOwner) { FrameLayout(this) }
    }

    abstract class Observer{
        val listeners = mutableSetOf<LiveData<*>>()
        abstract fun observe(exComp: ExComp)
    }
}