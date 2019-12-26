package com.example.newsapp

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.home.HomeAdapter
import com.example.newsapp.network.Results


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data :List<Results>?)
{
    val adapter = HomeAdapter()
    recyclerView.adapter = adapter
    adapter.submitList(data)

    /*
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
     */
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}


@BindingAdapter("newsTitle")
fun bindTitle(textView: TextView, newsTitle: String?) {
    newsTitle?.let {
        textView.text = it
    }
}


@BindingAdapter("newsAuthor")
fun bindAuthor(textView: TextView, newsAuthor: String?) {
    newsAuthor?.let {
        textView.text = it
    }
}


@BindingAdapter("newsSection")
fun bindSection(textView: TextView, newsSection: String?) {
    newsSection?.let {
        textView.text = it
    }
}


@BindingAdapter("newsDate")
fun bindDate(textView: TextView, newsDate: String?) {
    newsDate?.let {
        textView.text = it
    }
}


