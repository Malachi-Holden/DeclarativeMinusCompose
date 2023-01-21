package com.holden.declarativeminuscompose.exComp.coreWidgets

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier

fun ExComp.EditText(
    modifier: Modifier = Modifier(),
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit
) = BuildExComp(modifier, { context ->
    EditText(context).apply {
        hint = placeholder
        setText(text)
        addTextChangedListener(ExCompWatcher(onTextChanged))
    }
}){}

private class ExCompWatcher(val onTextChanged: (String) -> Unit): TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChanged((text ?: "") as String)
    }

    override fun afterTextChanged(p0: Editable?) {}
}