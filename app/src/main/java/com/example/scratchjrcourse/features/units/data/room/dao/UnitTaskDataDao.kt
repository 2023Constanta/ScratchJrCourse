package com.example.scratchjrcourse.features.units.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity

@Dao
interface UnitTaskDataDao {

    // Нахождение индексов общности для заданий блока
    @Query("select distinct id_of_mutual from unit_task_data where unit_id = :unitId AND task_id = :taskId")
    suspend fun getListOfIndOfMutual(unitId: Int, taskId: Int): List<Int>

    // Получение данных для одного экрана
    @Query("SELECT * FROM unit_task_data WHERE id_of_mutual = :idOfMutual AND task_id = :taskId AND unit_id = :unitId")
    suspend fun getMutualTasks(idOfMutual: Int, taskId: Int, unitId: Int): List<UnitTaskDataEntity>

    // Получение вопросов
    @Query("SELECT * FROM unit_task_data WHERE is_question =:isQuestion AND task_id = :taskId AND unit_id = :unitId")
    suspend fun getQuestions(
        unitId: Int,
        taskId: Int,
        isQuestion: Boolean
    ): List<UnitTaskDataEntity>

}