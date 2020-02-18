package com.facundomr.redditclient.ui.list

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facundomr.redditclient.RedditClientApplication
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

            val entriesResponse = RedditAPIClient.getEntries().data.children

            val authorIds = entriesResponse.map {
                it.data.author
            }
                .distinct()
                .joinToString()

            val userDataById = RedditAPIClient.getUsers(authorIds)

            val entries = entriesResponse.map {

                it.data.read = getPrefs().getBoolean(it.data.id, false)
                it.data.username = userDataById[it.data.author]?.name ?: ""
                it.data
            }

            data.value = entries
        }
    }

    private fun getPrefs() = RedditClientApplication.getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun markAsRead(item: EntryData) {
        getPrefs().edit().putBoolean(item.id, true).apply()
    }

}