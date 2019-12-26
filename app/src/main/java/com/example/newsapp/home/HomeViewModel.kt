package com.example.newsapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.Response
import com.example.newsapp.network.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


/**
 * The [ViewModel] that is attached to the [HomeFragment].
 */

private const val  API_KEY = "4c6a7f99-61b4-479d-8946-12e2538c3ccb"
private const val FIELDS = "headline,byline,thumbnail"

class HomeViewModel : ViewModel() {

    private val _response = MutableLiveData<List<Results>>()

    val response: LiveData<List<Results>>
        get() = _response






    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getAllNewsResponse()
    }


    /**
     * Gets News information from the Mars API Retrofit service and updates the
     * [Response] [List] and [NewsApiService] [LiveData]. The Retrofit service returns a
     * coroutine Deferred, which we await to get the result of the transaction.
     */
    fun getAllNewsResponse() {
        coroutineScope.launch {
            var getAllNewsResponseDeferred =NewsApi.retrofitService.getAllNewsResponse(
                API_KEY,
                FIELDS
            )
            try
            {
                _response.value = getAllNewsResponseDeferred.await().response.results
            } catch (e:Exception)
            {
                _response.value = ArrayList()
            }
        }
    }


    /*
    fun getNewsBySectionResponse(section : NewsApiSection)
    {
        coroutineScope.launch {
            var getNewsBySectionResponse = NewsApi.retrofitService.getNewsBySectionResponse(API_KEY,
                FIELDS,section.value)
            try
            {
                //_testAgain.value = "${getNewsBySectionResponse.await().response.results[0].sectionName}"
            }catch (e:Exception)
            {
                //_testAgain.value = "${e}"
            }
        }
    }


    fun updateSection(section: NewsApiSection) {
        getNewsBySectionResponse(section)
    }*/

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}