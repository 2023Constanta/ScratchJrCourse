package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UnitEntity::class,
        UnitTaskEntity::class,
        UnitTaskDataEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDb : RoomDatabase() {
    abstract fun getUnitDao(): UnitDao
    abstract fun getUnitTaskDao(): UnitTaskDao
    abstract fun getUnitTaskDataDao(): UnitTaskDataDao

}