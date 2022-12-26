package com.example.declarativeminuscompose.exComp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class ExComp(val lifecycleOwner: LifecycleOwner, observer: Observer? = null, val factory: Context.() -> View) {
    val children = mutableListOf<ExComp>()
    var modifier: Modifier? = null

    var observer = observer
        set(value) {
            field = value
            field?.observe(this)
        }

    fun <T>LiveData<T>.bind(){
        if (observer?.listeners?.contains(this) == true) return
        observer?.listeners?.put(this, value as Any)
        observe(lifecycleOwner){ newVal ->
            val prev = observer?.listeners?.get(this)
            if (prev == newVal) return@observe
            observer?.listeners?.put(this, newVal as Any)
            observer?.observe(this@ExComp)
        }
    }

    fun Layout(root: Context.() -> ViewGroup, content: ExComp.() -> Unit){
        children.add(
            ExComp(lifecycleOwner, observer, root).apply(content)
        )
    }

    fun buildView(context: Context): View = context.factory().apply {
        var current = modifier
        while (current?.onUpdate != null){
            current.onUpdate?.let { it(this) }
            current = current.next
        }
        if (this is ViewGroup) children.forEach { child -> addView(child.buildView(context)) }
    }

    companion object {
        fun default(lifecycleOwner: LifecycleOwner) = ExComp(lifecycleOwner) { FrameLayout(this) }
    }

    abstract class Observer{
        val listeners = mutableMapOf<LiveData<*>, Any>()
        val rememberedData = mutableMapOf<Int, Any>()
        var rememberedCount = 0

        fun <T>store(item: T): Int{
            rememberedData[rememberedCount] = item as Any
            return rememberedCount.also { rememberedCount ++ }
        }

        fun <T>retrieve(key: Int): T? = rememberedData[key] as? T


        abstract fun observe(exComp: ExComp)
    }
}