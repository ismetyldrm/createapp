package com.example.diablo

import android.app.Application

class MyApp : Application() {

    lateinit var credentialsManager: CredentialsManager

    override fun onCreate() {
        super.onCreate()
        credentialsManager = CredentialsManager(applicationContext)
    }
}
