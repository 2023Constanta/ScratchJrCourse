package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchjrcourse.features.units.data.room.AppDb
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskDataPictureEntity
import com.example.scratchjrcourse.features.units.data.room.entity.UnitTaskEntity
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData
import com.example.scratchjrcourse.features.units.domain.domain.model.Picture
import com.example.scratchjrcourse.features.units.presentation.ui.list.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 */
class UnitViewModel(
    private val db: AppDb
) : ViewModel() {
    private val _unitTasks = MutableLiveData<List<CourseUnitTask>>()
    val unitTasks get() = _unitTasks

    private val _unitTaskData = MutableLiveData<List<CourseUnitTaskData>>()
    val unitTaskData get() = _unitTaskData


    private val _unitTaskDataWPics = MutableLiveData<List<CourseUnitTaskData>>()
    val unitTaskDataWPics get() = _unitTaskDataWPics
    fun getDetails(unitId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = db.getUnitTaskDao().getTasksByUnitId(unitId).map { it.toDomain() }
                withContext(Dispatchers.Main) {
                    _unitTasks.value = list
                }
            }
        }
    }

//    fun loadPictureForTaskData(unitId: Int, taskId: Int, taskDataId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//
//            }
//        }
//    }

//    fun getDataForTasks(unitId: Int, taskId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val list =
//                    db.getUnitTaskDataDao().getTasksByUnitId(unitId, taskId).map { it.toDomain() }
////
////                val picture =
////                    db.getPicturesDao().getPictures(1, 1, 1)
////
////                Log.d("UNITVIM", "getDataForTasks: $picture")
////                val list2 = mutableListOf<CourseUnitTaskData>()
////                for (l in list) {
////                    val picture =
////                        db.getPicturesDao().getPictures(l.unitId, l.taskId, taskDataId = l.id)
////                    val l1 = l.copy(pics = listOf(picture.picture))
////                    list2.add(
////                        l1
////                    )
////
////                }
//                withContext(Dispatchers.Main) {
//                    _unitTaskData.value = list
//                }
//            }
//        }
//    }

    fun getPortionOfDataByIdOfMutual(idOfMut: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val listOfMutualData =
                    db.getUnitTaskDataDao().getMutualTasks(idOfMut).map { it.toDomain() }

                val portion = mutableListOf<CourseUnitTaskData>()

                for (p in listOfMutualData) {
                    val n = p.copy(
                        pics = db.getPicturesDao().getPicturesByTasksData(p.id)?.map { it.picture }
                    )
                    portion.add(n)
                }

                withContext(Dispatchers.Main) {
                    _unitTaskDataWPics.value = portion
                }

            }
        }
    }
}

fun UnitTaskEntity.toDomain(): CourseUnitTask =
    CourseUnitTask(id, unitId, name)

fun UnitTaskDataEntity.toDomain(): CourseUnitTaskData =
    CourseUnitTaskData(id, unitId, taskId, text, idOfMutual = idOfMutual, pics = listOf(), arePicsVertical = arePicsVert)

fun UnitTaskDataPictureEntity.toDomain(): Picture = Picture(
    id, unitId, taskId, taskDataId, picture
)
