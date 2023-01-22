package com.holden.declarativeminuscompose.exComp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class ExComp(
    val lifecycleOwner: LifecycleOwner,
    val comptext: Comptext,
    val treeId: List<String> = listOf(),
    val factory: ExComp.(Context) -> View
) {
    val children = mutableListOf<ExComp>()
    var modifier: Modifier? = null

    init {
        // TODO: register lifecycle events and get rid of lifecyle owner after destruction
    }


    fun observe(observation: (ExComp) -> Unit) {
        comptext.setObserver(this) {
            children.removeAll { true }
            itemId = 0
            observation(it)
        }
    }

    fun <T> LiveData<T>.bind() {
        comptext.observer?.bind(lifecycleOwner, this@ExComp, this, value)
    }

    fun nextTreeId() = treeId + "${children.size}"

    fun BuildExComp(
        modifier: Modifier = Modifier(),
        root: ExComp.(Context) -> View,
        content: ExComp.() -> Unit
    ) {
        children.add(
            ExComp(lifecycleOwner, comptext, nextTreeId(), root).apply {
                this.modifier = modifier
                content()
            }
        )
    }

    var itemId = 0

    fun <T> store(item: T) =
        comptext.retrieveDatabase(treeId).put(itemId, item as Any).also { itemId++ }

    fun <T> retrieve(id: Int) = comptext
        .retrieveDatabase(treeId)[id] as? T

    fun <T> remember(initial: T): T {
        val item = retrieve<T>(itemId)
        if (item != null) {
            itemId++
            return item
        }
        store(initial)
        itemId++
        return initial
    }

    fun buildView(context: Context): View = factory(context).apply {
        var current = modifier
        while (current?.onUpdate != null) {
            current.onUpdate?.let { it(this) }
            current = current.next
        }
        if (this is ViewGroup) children.forEach { child -> addView(child.buildView(context)) }
    }

    companion object {
        fun default(lifecycleOwner: LifecycleOwner, comptext: Comptext) =
            ExComp(lifecycleOwner, comptext) { context -> FrameLayout(context) }
    }

    abstract class Observer{
        val listeners = mutableMapOf<LiveData<*>, Any>()

        fun <T> bind(lifecycleOwner: LifecycleOwner, exComp: ExComp, liveData: LiveData<T>, value: T?) {
            if (listeners.contains(liveData)) return
            listeners[liveData] = value as Any
            liveData.observe(lifecycleOwner) { newVal ->
                val prev = listeners[liveData]
                if (prev == newVal) return@observe
                listeners[liveData] = newVal as Any
                observe(exComp)
            }
        }

        abstract fun observe(exComp: ExComp)
    }

    class Comptext(
        observer: Observer? = null
    ) {
        private var _observer = observer
        val observer: Observer?
            get() = _observer

        val rememberedData = mutableMapOf<List<String>, MutableMap<Int, Any>>()

        fun setObserver(exComp: ExComp, observation: (ExComp) -> Unit) {
            _observer = object : Observer() {
                override fun observe(exComp: ExComp) {
                    observation(exComp)
                }
            }
            _observer?.observe(exComp)
        }

        fun storeDatabase(key: List<String>, database: MutableMap<Int, Any>) {
            rememberedData[key] = database
        }

        fun retrieveDatabase(key: List<String>): MutableMap<Int, Any> {
            val database = rememberedData[key]
            if (database != null) {
                return database
            }
            val result = mutableMapOf<Int, Any>()
            rememberedData[key] = result
            return result
        }

    }
}