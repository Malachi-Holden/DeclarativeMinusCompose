package com.holden.declarativeminuscompose.exComp

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

open class ExCompActivity: AppCompatActivity(){
    fun setContent(content: ExComp.()->Unit){
        val exCompViewModel: ExCompViewModel by viewModels()
        val exComp = ExComp.default(this, exCompViewModel.comptext)
        exComp
            .observe { ex ->
                ex.content()
                setContentView(ex.buildView(this))
            }
    }
}

class ExCompViewModel(): ViewModel(){
    var comptext = ExComp.Comptext()
}