package com.facundomr.redditclient

import android.app.Application

class RedditClientApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: RedditClientApplication
        fun getContext() = instance.applicationContext
    }

}