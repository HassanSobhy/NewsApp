package com.example.newsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.NewsListItemBinding
import com.example.newsapp.network.Results

class HomeAdapter :
    ListAdapter<Results, HomeAdapter.ResultsViewHolder>(
        DiffCallback
    ) {

    class ResultsViewHolder(private var binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(results: Results) {
            binding.results = results
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Results]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.webTitle == newItem.webTitle
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val results = getItem(position)
        holder.bind(results)
    }


}