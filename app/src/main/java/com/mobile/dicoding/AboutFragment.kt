package com.mobile.dicoding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mobile.dicoding.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var listener: AboutFragmentListener
    private lateinit var binding: FragmentAboutBinding

    interface AboutFragmentListener {
        fun changeCollapsingToolbarState(expanded: Boolean)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listener.changeCollapsingToolbarState(false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listener.changeCollapsingToolbarState(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AboutFragmentListener) listener = context
    }
}