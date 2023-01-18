package com.example.declarativeminuscompose.exComp.coreWidgets

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier
import com.example.declarativeminuscompose.util.Container

enum class Orientation {
    Horizontal,
    Vertical
}

fun ExComp.LazyList(
    modifier: Modifier = Modifier(),
    orientation: Orientation = Orientation.Vertical,
    content: LazyListScope.() -> Unit
) {
    val layoutOrientation = when (orientation) {
        Orientation.Horizontal -> LinearLayout.HORIZONTAL
        Orientation.Vertical -> LinearLayout.VERTICAL
    }
    BuildExComp(
        modifier,
        { context ->
            val layout = LinearLayoutManager(context).apply { this.orientation = layoutOrientation }
            val layoutContainer = remember(Container(layout.onSaveInstanceState()))
            layout.onRestoreInstanceState(layoutContainer.item)
            RecyclerView(context).apply {
                layoutManager =
                    layout
                adapter = LazyListAdapter(LazyListScope(this@LazyList).apply(content))
                addOnScrollListener(object : OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        layoutContainer.item =
                            (layoutManager as? LinearLayoutManager)?.onSaveInstanceState() ?: return
                    }
                })
            }
        }) {

    }
}

class LazyListScope(val parent: ExComp) {
    val children = mutableListOf<ExComp>()
    fun item(content: ExComp.() -> Unit) {
        children.add(
            ExComp(
                parent.lifecycleOwner,
                parent.comptext,
                parent.nextTreeId()
            ) { context -> FrameLayout(context) }.apply(content)
        )
    }
}

class LazyListAdapter(val scope: LazyListScope) : Adapter<LazyListAdapter.LazyListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LazyListViewHolder = LazyListViewHolder(FrameLayout(parent.context))

    override fun onBindViewHolder(holder: LazyListViewHolder, position: Int) {
        holder.bind(scope.children[position])
    }

    override fun getItemCount(): Int = scope.children.size

    class LazyListViewHolder(val root: ViewGroup) : ViewHolder(root) {
        fun bind(exComp: ExComp) {
            root.removeAllViews()
            root.addView(exComp.buildView(root.context))
        }

    }
}