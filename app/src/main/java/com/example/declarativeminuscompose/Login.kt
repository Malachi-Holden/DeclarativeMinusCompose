package com.example.declarativeminuscompose

import androidx.lifecycle.MutableLiveData
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.Modifier
import com.holden.declarativeminuscompose.exComp.coreWidgets.Button
import com.holden.declarativeminuscompose.exComp.coreWidgets.EditText
import com.holden.declarativeminuscompose.exComp.coreWidgets.Text
import com.holden.declarativeminuscompose.exComp.coreWidgets.VerticalLayout
import com.holden.declarativeminuscompose.exComp.textSize
import kotlinx.coroutines.flow.MutableStateFlow

fun ExComp.Login() {
    VerticalLayout {
        val username = remember(MutableLiveData(""))
        username.bind()
        val password = remember(MutableLiveData(""))
        password.bind()
        Text("Welcome to the app", modifier = Modifier().textSize(45f))
        EditText(
            placeholder = "Username",
            text = username.value ?: "",
            onTextChanged = { username.value = it })
        EditText(
            placeholder = "Password",
            text = password.value ?: "",
            onTextChanged = { password.value = it })
        Button(text = "Login", onClick = {}) // TODO: needs navigation
        Button(text = "create account", onClick = { }) // TODO: needs navigation
    }
}