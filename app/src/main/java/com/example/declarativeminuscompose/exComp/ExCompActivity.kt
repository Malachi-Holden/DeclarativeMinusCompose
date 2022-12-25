package com.example.declarativeminuscompose.exComp

import android.text.Spannable.Factory
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

open class ExCompActivity: AppCompatActivity(){
    fun setContent(content: ExComp.()->Unit){
        val exCompViewModel: ExCompViewModel by viewModels { ExCompViewModel.Factory(this) }
        exCompViewModel.activityExComp.also{ exComp ->
            exComp.observer = object: ExComp.Observer() {
                override fun observe(exComp: ExComp) {
                    exComp.children.removeAll { true }
                    exComp.content()
                    setContentView(exComp.buildView(this@ExCompActivity))
//                recreate()
                }
            }
        }
    }
}

class ExCompViewModel(lifecycleOwner: LifecycleOwner): ViewModel(){
    val activityExComp = ExComp.default(lifecycleOwner)

    companion object {
        fun Factory(lifecycleOwner: LifecycleOwner): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return ExCompViewModel(lifecycleOwner) as T
            }
        }
    }
}