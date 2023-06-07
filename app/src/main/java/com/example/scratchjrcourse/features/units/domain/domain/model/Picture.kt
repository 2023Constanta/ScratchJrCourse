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
    val picture: Bitmap
) {
}