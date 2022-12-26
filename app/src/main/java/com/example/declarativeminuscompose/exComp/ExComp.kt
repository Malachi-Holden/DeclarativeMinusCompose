package com.example.declarativeminuscompose.exComp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class ExComp(val lifecycleOwner: LifecycleOwner,
             observer: Observer? = null,
             val treeId: List<String> = listOf(),
             val factory: Context.() -> View) {
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

    fun nextTreeId() = treeId + "${children.size}"

    fun BuildExComp(modifier: Modifier = Modifier(), root: Context.() -> View, content: ExComp.() -> Unit){
        children.add(
            ExComp(lifecycleOwner, observer, nextTreeId(), root).apply{
                this.modifier = modifier
                content()
            }
        )
    }

    fun <T>store(item: T) = observer?.store(treeId, item)

    fun <T>retrieve(key: List<String>) = observer?.retrieve<T>(key)

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
        val rememberedData = mutableMapOf<List<String>, Any>()

        fun <T>store(key: List<String>, item: T){
            rememberedData[key] = item as Any
        }

        fun <T>retrieve(key: List<String>): T? = rememberedData[key] as? T


        abstract fun observe(exComp: ExComp)
    }
}