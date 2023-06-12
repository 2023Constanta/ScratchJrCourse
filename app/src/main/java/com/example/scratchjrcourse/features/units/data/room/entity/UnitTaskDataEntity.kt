package com.example.scratchjrcourse.features.units.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_task_data")
data class UnitTaskDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "unit_id")
    val unitId: Int,
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "id_of_mutual")
    val idOfMutual: Int,
    val text: String,
    @ColumnInfo(name = "are_pics_vert")
    val arePicsVert: Boolean,
    @ColumnInfo(name = "is_question")
    val isQuestion: Boolean,
    @ColumnInfo(name = "ans_text")
    val answerText: String? = null
)
