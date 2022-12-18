package com.example.quizapp

import android.app.Application
import com.example.quizapp.model.Repo
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    lateinit var repo: Repo

    override fun onCreate() {
        super.onCreate()

        repo = Repo()
    }
}