package com.example.scratchjrcourse.features.units.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity

@Dao
interface UnitTaskDataDao {

    // Нахождение индексов общности для заданий блока
    @Query("select distinct id_of_mutual from unit_task_data where unit_id = :unitId AND task_id = :taskId")
    suspend fun getListOfIndOfMutual(unitId: Int, taskId: Int): List<Int>

    // Получение количества экранов для ПОРЦИИ данных с опред. разделом и заданием
    @Query("SELECT COUNT (DISTINCT id_of_mutual) FROM unit_task_data WHERE unit_id =:unitId AND task_id = :taskId")
    suspend fun getCountOfMutualTasks(unitId: Int, taskId: Int): Int

    // Получение данных для одного экрана
    @Query("SELECT * FROM unit_task_data WHERE id_of_mutual = :idOfMutual AND task_id = :taskId AND unit_id = :unitId")
    suspend fun getMutualTasks(idOfMutual: Int, taskId: Int, unitId: Int): List<UnitTaskDataEntity>

}