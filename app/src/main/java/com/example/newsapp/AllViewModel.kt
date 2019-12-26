package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.NewsApiSection
import com.example.newsapp.network.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val API_KEY = "4c6a7f99-61b4-479d-8946-12e2538c3ccb"
private const val FIELDS = "headline,byline,thumbnail"


class AllViewModel(section: NewsApiSection) : ViewModel() {

    private val _response = MutableLiveData<List<Results>>()

    val response: LiveData<List<Results>>
        get() = _response


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getNewsBySectionResponse(section)
    }


    fun getNewsBySectionResponse(section: NewsApiSection) {
        coroutineScope.launch {
            var getNewsBySectionResponse = NewsApi.retrofitService.getNewsBySectionResponse(
                API_KEY,
                FIELDS, section.value
            )
            try {
                _response.value = getNewsBySectionResponse.await().response.results
            } catch (e: Exception) {
                _response.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}