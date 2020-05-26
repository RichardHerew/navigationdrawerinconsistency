package com.mobile.dicoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.dicoding.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.fragHomeStoryRv
        val adapter = StoryAdapter(
            StoryListener { id ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeDestToStoryDetailsFragment(id)
                )
            }
        )
        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.decoration_space, null)
            if (drawable != null)
                setDrawable(drawable)
        }

        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
        adapter.submitList(StoriesData.listData)
    }
}