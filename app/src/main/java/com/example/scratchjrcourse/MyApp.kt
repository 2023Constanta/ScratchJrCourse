package com.example.scratchjrcourse

import android.app.Application
import com.example.scratchjrcourse.features.units.di.dataModule
import com.example.scratchjrcourse.features.units.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(dataModule, presentationModule)
        }
    }
}