package com.facundomr.redditclient.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundomr.redditclient.api.RedditAPIClient
import com.facundomr.redditclient.model.EntryData
import kotlinx.coroutines.launch

class EntriesListViewModel: ViewModel() {

    val data = MutableLiveData<List<EntryData>>()

    init {
        loadData()
    }

    private fun loadData() {

        viewModelScope.launch {

            val entries = RedditAPIClient.getEntries().data.children.map {
                it.data
            }

            data.value = entries
        }
    }
}