package com.holden.declarativeminuscompose.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText

class BetterEditText(context: Context): AppCompatEditText(context) {
    val watchers = mutableListOf<TextWatcher>()

    fun setTextChangedListener(onTextChanged: (String)->Unit){
        removeTextChangedListeners()
        addTextChangedListener(onTextChanged)
    }

    fun addTextChangedListener(onTextChanged: (String)->Unit) {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChanged(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        watchers.add(watcher)
        addTextChangedListener(watcher)
    }

    fun removeTextChangedListeners(){
        for (watcher in watchers){
            removeTextChangedListener(watcher)
        }
        watchers.removeAll { true }
    }
}