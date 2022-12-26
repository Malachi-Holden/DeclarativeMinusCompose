package com.example.declarativeminuscompose.exComp.coreWidgets

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.declarativeminuscompose.exComp.ExComp
import com.example.declarativeminuscompose.exComp.Modifier

enum class Orientation{
    Horizontal,
    Vertical
}

fun ExComp.LazyList(orientation: Orientation = Orientation.Vertical, content: LazyListScope.()->Unit){
    val layoutOrientation = when(orientation){
        Orientation.Horizontal -> LinearLayout.HORIZONTAL
        Orientation.Vertical -> LinearLayout.VERTICAL
    }
    Layout({
        RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this.context).apply { this.orientation = layoutOrientation }
            adapter = LazyListAdapter(LazyListScope(this@LazyList).apply(content))
        }
    }){}
}

class LazyListScope(val parent: ExComp){
    val children = mutableListOf<ExComp>()
    fun item(content: ExComp.()->Unit){
        children.add(ExComp(parent.lifecycleOwner, parent.observer) { FrameLayout(this) }.apply(content))
    }
}

class LazyListAdapter(val scope: LazyListScope): Adapter<LazyListAdapter.LazyListViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): LazyListViewHolder = LazyListViewHolder(FrameLayout(parent.context))

    override fun onBindViewHolder(holder: LazyListViewHolder, position: Int) {
        holder.bind(scope.children[position])
    }

    override fun getItemCount(): Int = scope.children.size

    class LazyListViewHolder(val root: ViewGroup): ViewHolder(root) {
        fun bind(exComp: ExComp){
            root.removeAllViews()
            root.addView(exComp.buildView(root.context))
        }

    }
}