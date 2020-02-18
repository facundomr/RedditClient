package com.facundomr.redditclient.api

import com.facundomr.redditclient.model.EntriesResponse
import com.facundomr.redditclient.model.UserData
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditAPIInterface {

    @GET("top.json")
    suspend fun getEntries(): EntriesResponse

    @GET("api/user_data_by_account_ids.json")
    suspend fun getUsers(@Query("ids") authorIds: String): Map<String, UserData>
}