package com.holden.declarativeminuscompose.exComp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import java.lang.ref.WeakReference

class ExComp(
    lifecycleOwner: LifecycleOwner,
    val comptext: Comptext,
    val treeId: List<String> = listOf(),
    val factory: ExComp.(Context) -> View
) {
    val lifecycleOwnerRef = WeakReference(lifecycleOwner)
    val children = mutableListOf<ExComp>()
    var modifier: Modifier? = null

    var view: View? = null

    fun invalidate(){
        view = null
    }

    fun observe(observation: () -> Unit) {
        comptext.setObserver {
            children.removeAll { true }
            itemId = 0
            observation()
        }
    }

    fun <T> LiveData<T>.bind() {
        comptext.observer?.bind(lifecycleOwner(), this, value)
    }

    fun lifecycleOwner() = lifecycleOwnerRef.get() ?: throw DeallocatedLifecycleException()

    fun nextTreeId() = treeId + "${children.size}"

    fun BuildExComp(
        modifier: Modifier = Modifier(),
        root: ExComp.(Context) -> View,
        content: ExComp.() -> Unit
    ) {
        children.add(
            ExComp(lifecycleOwner(), comptext, nextTreeId(), root).apply {
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

    fun View.applyModifier(){
        var current = modifier
        while (current?.onUpdate != null) {
            current.onUpdate?.let { it(this) }
            current = current.next
        }
    }

    fun buildView(context: Context): View = (view ?: factory(context)).apply {
        view = this
        applyModifier()
        if (this is ViewGroup) children.forEach { child -> addView(child.buildView(context)) }
    }

    companion object {
        fun default(lifecycleOwner: LifecycleOwner, comptext: Comptext) =
            ExComp(lifecycleOwner, comptext) { context -> FrameLayout(context) }
    }

    abstract class Observer{
        val listeners = mutableMapOf<LiveData<*>, Any>()

        fun <T> bind(lifecycleOwner: LifecycleOwner, liveData: LiveData<T>, value: T?) {
            if (listeners.contains(liveData)) return
            listeners[liveData] = value as Any
            liveData.observe(lifecycleOwner) { newVal ->
                val prev = listeners[liveData]
                if (prev == newVal) return@observe
                listeners[liveData] = newVal as Any
                observe()
            }
        }

        abstract fun observe()
    }

    class Comptext(
        observer: Observer? = null
    ) {
        private var _observer = observer
        val observer: Observer?
            get() = _observer

        val rememberedData = mutableMapOf<List<String>, MutableMap<Int, Any>>()

        fun setObserver(observation: () -> Unit) {
            _observer = object : Observer() {
                override fun observe() {
                    observation()
                }
            }
            _observer?.observe()
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

class DeallocatedLifecycleException:
    Exception("Attempted to access a lifecycle that has been destroyed and deallocated")