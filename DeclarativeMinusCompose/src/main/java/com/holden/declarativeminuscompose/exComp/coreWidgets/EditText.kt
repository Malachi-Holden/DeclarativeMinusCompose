package com.holden.declarativeminuscompose.exComp.coreWidgets

import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier
import com.holden.declarativeminuscompose.util.BetterEditText

fun ExComp.EditText(
    modifier: Modifier = Modifier(),
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit
) = BuildExComp(modifier, { context ->
    BetterEditText(context).apply {
        hint = placeholder
        setText(text)
        setTextChangedListener(onTextChanged)
    }
}){}