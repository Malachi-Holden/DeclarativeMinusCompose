package com.example.declarativeminuscompose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.holden.declarativeminuscompose.exComp.ExCompView

class SampleFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ExCompView(requireActivity(), requireContext()){
            Login()
        }
    }
}