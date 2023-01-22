package com.holden.declarativeminuscompose.exComp.coreWidgets

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier

fun ExComp.EditText(
    modifier: Modifier = Modifier(),
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit
) = BuildExComp(modifier, { context ->
    ExcompEditText(context).apply {
        hint = placeholder
        setText(text)
        textChanged = onTextChanged
    }
}){}

class ExcompEditText(context: Context): AppCompatEditText(context){
    var textChanged: ((String) -> Unit)? = null
    init {
        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textChanged?.invoke(s.toString())
            }

            override fun afterTextChanged(s: Editable?) { }

        })
    }
}