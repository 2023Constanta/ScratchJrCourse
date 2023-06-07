package com.example.scratchjrcourse.features.units.data.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}
