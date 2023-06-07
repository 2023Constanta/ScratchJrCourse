package com.example.scratchjrcourse.features.units.domain.domain.model

import android.graphics.Bitmap

/**
 * Элемент данных [CourseUnitTask] с текстом и картинками. Картинок может быть до 3 штук
 */
data class CourseUnitTaskData(
    val id: Int,
    val unitId: Int,
    val taskId: Int,
    val text: String?,
    val pics: List<ByteArray>?,
    val idOfMutual: Int,
    val arePicsVertical: Boolean? = false
)