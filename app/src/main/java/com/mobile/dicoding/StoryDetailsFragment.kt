package com.mobile.dicoding

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.textview.MaterialTextView
import com.mobile.dicoding.databinding.FragmentStoryDetailsBinding

class StoryDetailsFragment : Fragment() {
    private lateinit var listener: StoryDetailsFragmentListener
    private lateinit var binding: FragmentStoryDetailsBinding
    private var storyId: Int = 0
    private val textAppearance by lazy {
        val value = TypedValue()
        context?.theme?.resolveAttribute(R.attr.textAppearanceBody2, value, true)
        value.data
    }
    private val onBackgroundColor  by lazy {
        val value = TypedValue()
        context?.theme?.resolveAttribute(R.attr.colorOnBackground, value, true)
        value.data
    }

    interface StoryDetailsFragmentListener {
        fun changeCollapsingToolbarState(expanded: Boolean)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_story_details, container, false)
        with (arguments) {
            val arguments = this ?: return@with
            storyId = StoryDetailsFragmentArgs.fromBundle(arguments).storyId
        }
        listener.changeCollapsingToolbarState(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storyData = StoriesData.listData[storyId]
        val storyDetail = StoryDetailsData.listData[storyId]
        binding.fragStoryDetailsTitle.text = storyData.title
        binding.fragStoryDetailsSummaryText.text = storyDetail.summary
        for (storyContent in storyDetail.content) {
            binding.fragStoryDetailsContent.addView(
                createTextView(
                    storyContent
                )
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StoryDetailsFragmentListener) listener = context
    }

    private fun createTextView(text: String): MaterialTextView? {
        val context = context ?: return null
        return MaterialTextView(context).apply {
            this.text = StringBuilder().append(text).append("\n")
            setTextAppearance(textAppearance)
            setTextColor(onBackgroundColor)
            setLineSpacing(resources.getDimension(R.dimen.line_spacing_extra), 1f)
        }
    }
}