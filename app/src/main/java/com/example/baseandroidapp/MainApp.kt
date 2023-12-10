package com.example.baseandroidapp

import android.app.Application
import com.example.baseandroidapp.di.mainModule
import com.example.baseandroidapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)
            modules(listOf(mainModule, viewModelModule))
        }
    }
}