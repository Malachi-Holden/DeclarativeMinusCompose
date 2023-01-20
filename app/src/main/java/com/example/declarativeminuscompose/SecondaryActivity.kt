package com.example.declarativeminuscompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.holden.declarativeminuscompose.exComp.ExComp
import com.holden.declarativeminuscompose.exComp.ExCompView
import com.holden.declarativeminuscompose.exComp.coreWidgets.Button
import com.holden.declarativeminuscompose.exComp.coreWidgets.Text
import com.holden.declarativeminuscompose.exComp.coreWidgets.VerticalLayout

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ExCompView(this, this){
            val counterData = remember(MutableLiveData(0))
            counterData.bind()
            VerticalLayout {
                Button(onClick = { counterData.value = (counterData.value ?: 0) + 1 }, text = "increment")
                CrazyEmbeddingView(6, "${counterData.value}")
            }
        })
    }
}

// cool demonstration that it works
fun ExComp.CrazyEmbeddingView(depth: Int, message: String){
    if (depth <= 0){
        Text(message)
        return
    }
    VerticalLayout {
        CrazyEmbeddingView(depth - 1, message)
        CrazyEmbeddingView(depth - 1, message)
    }
}