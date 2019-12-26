package com.example.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.network.NewsApiSection

class AllViewModelFactory(val section: NewsApiSection) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllViewModel::class.java)) {
            return AllViewModel(section) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}