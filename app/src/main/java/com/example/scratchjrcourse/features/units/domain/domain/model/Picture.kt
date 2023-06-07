package com.example.scratchjrcourse.features.units.domain.domain.model

import android.graphics.Bitmap

/**
 * Картинка. Берется из бд
 */
data class Picture(
    val id: Int,
    val unitId: Int,
    val taskId: Int,
    val taskDataId: Int,
    val picture: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Picture

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