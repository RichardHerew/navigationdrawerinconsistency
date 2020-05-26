package com.mobile.dicoding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.dicoding.databinding.ViewholderStoryBinding

class StoryAdapter(
    private val clickListener: StoryListener
) : ListAdapter<Story, StoryAdapter.ViewHolder>(StoryDiffCallback()) {
    class ViewHolder private constructor(private val binding: ViewholderStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Story,
            clickListener: StoryListener
        ) {
            binding.story = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewholderStoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

class StoryDiffCallback : DiffUtil.ItemCallback<Story>() {
    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }
}

class StoryListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(story: Story) = clickListener(story.id)
}