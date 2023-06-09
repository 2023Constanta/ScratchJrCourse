package com.example.scratchjrcourse.features.units.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataPictureEntity

@Dao
interface PicturesDao {
    @Query("SELECT * FROM unit_task_data_pictures WHERE task_data_id =:taskDataId")
    suspend fun getPicturesByTasksData(taskDataId: Int): List<UnitTaskDataPictureEntity>?
}