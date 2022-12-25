package com.example.declarativeminuscompose

import android.os.Bundle
import com.example.declarativeminuscompose.exComp.*
import com.example.declarativeminuscompose.exComp.coreWidgets.*

class MainActivity : ExCompActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LazyList(Orientation.Horizontal) {
                item {
                    Text("hello")
                }
                item{
                    Button({}, "goodbye")
                }
                repeat(100){
                    item{
                        Text("again: $it")
                    }
                }
            }

        }
    }
}
