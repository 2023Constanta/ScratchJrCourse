package com.example.scratchjrcourse.features.units.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_task_data_pictures")
data class UnitTaskDataPictureEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "unit_id")
    val unitId: Int,
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "task_data_id")
    val taskDataId: Int,
    val picture: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnitTaskDataPictureEntity

        if (id != other.id) return false
        if (unitId != other.unitId) return false
        if (taskId != other.taskId) return false
        if (taskDataId != other.taskDataId) return false
        if (!picture.contentEquals(other.picture)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + unitId
        result = 31 * result + taskId
        result = 31 * result + taskDataId
        result = 31 * result + picture.contentHashCode()
        return result
    }
}
