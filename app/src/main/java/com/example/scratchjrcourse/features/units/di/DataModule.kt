package com.example.scratchjrcourse.features.units.di

import androidx.room.Room
import com.example.scratchjrcourse.features.units.data.room.AppDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            AppDb::class.java,
            "scratch_data"
        ).createFromAsset("init_db/init_data.db").build()
    }
}