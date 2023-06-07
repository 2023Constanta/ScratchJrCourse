package com.example.scratchjrcourse.features.units.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scratchjrcourse.features.units.data.room.dao.PicturesDao
import com.example.scratchjrcourse.features.units.data.room.dao.UnitDao
import com.example.scratchjrcourse.features.units.data.room.dao.UnitTaskDao
import com.example.scratchjrcourse.features.units.data.room.dao.UnitTaskDataDao
import com.example.scratchjrcourse.features.units.data.room.entity.UnitEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataPictureEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskEntity

@Database(
    entities = [
        UnitEntity::class,
        UnitTaskEntity::class,
        UnitTaskDataEntity::class,
        UnitTaskDataPictureEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {

    abstract fun getUnitDao(): UnitDao
    abstract fun getUnitTaskDao(): UnitTaskDao
    abstract fun getUnitTaskDataDao(): UnitTaskDataDao
    abstract fun getPicturesDao(): PicturesDao

}