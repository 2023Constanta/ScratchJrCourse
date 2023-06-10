package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchjrcourse.features.units.data.room.AppDb
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData
import com.example.scratchjrcourse.features.units.domain.domain.model.DataPortion
import com.example.scratchjrcourse.features.units.domain.domain.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 */
class UnitViewModel(
    private val db: AppDb
) : ViewModel() {
    // Задания блока
    private val _unitTasks = MutableLiveData<List<CourseUnitTask>>()
    val unitTasks get() = _unitTasks

    // Задания курса с картинками
    private val _unitTaskDataWPics = MutableLiveData<List<CourseUnitTaskData>?>()
    val unitTaskDataWPics get() = _unitTaskDataWPics

    // Список индексов
    private var _listOfIndOfMutual = MutableLiveData<List<Int>>()
    val listOfIndOfMutual get() = _listOfIndOfMutual

    val dataPortions = mutableListOf<DataPortion>()
//    val dataPortions get() = _dataPortions

    val dataPortionsLV = MutableLiveData<List<DataPortion>>()

    /**
     * Получение порций данных для задания раздела
     */
//    fun getPortionsOfData(unitId: Int, taskId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                // 1. Идет получение индексов общности. Для каждого экранчика только его данные
//                val inds = db.getUnitTaskDataDao().getListOfIndOfMutual(unitId, taskId)
//
//                val dataPortions = mutableListOf<DataPortion>()
//                // 2. Проходится по списку индексов
//                for (i in inds) {
//
//                    Log.d(TAG, "getPortion: ИД ОБЩ: $i")
//
//
////                    getPortionOfDataByIdOfMutual(i, unitId, taskId)
//
//                    val dataPortion = _unitTaskDataWPics.value?.let { DataPortion(i, it) }
//
//                    Log.d(TAG, "getPortionsOfData: dataPortion: $dataPortion")
//
//                    Log.d(TAG, "getPortionsOfData: ДАН ПОРЦ: $dataPortion")
//                    Log.d(TAG, "getPortionsOfData: ДАН ПОРЦ: ${dataPortion?.id}")
//                    if (dataPortion != null) {
//                        dataPortions.add(dataPortion)
//                    }
//                }
//
//                Log.d(TAG, "getPortionsOfData: индексы ")
//
//
//                withContext(Dispatchers.Main) {
//                    Log.d(TAG, "getPortionsOfData: СПИСОК ПОРЦ ДАН: $dataPortions")
//                    dataPortionsLV.value = dataPortions
//                    Log.d(TAG, "getPortionsOfData: DP : ${dataPortionsLV.value}")
//                }
//
//            }
//        }
//    }


    fun getPortionsOfData(unitId: Int, taskId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // 1. Идет получение индексов общности. Для каждого экранчика только его данные
                //getListIndOfMutual(unitId, taskId)
                val temp = db
                    .getUnitTaskDataDao()
                    .getListOfIndOfMutual(unitId, taskId)

                val dataPortions = mutableListOf<DataPortion>()
                // 2. Проходится по списку индексов
                for (i in temp) {

//                    Log.d(TAG, "getPortion: ИД ОБЩ: $i")
//                    Log.d(TAG, "getPortion: ИД ОБЩ: $")

                    //getPortionOfDataByIdOfMutual(i, unitId, taskId)
                    val portionByMutal = db.getUnitTaskDataDao()
                        .getMutualTasks(i, taskId, unitId)
                        .map {
                            it.toDomain()
                        }
                        .map {
                            it.copy(
                                pics = db.getPicturesDao().getPicturesByTasksData(it.id)
                                    ?.map { it.picture }
                            )
                        }

                    val dataPortion = DataPortion(i, portionByMutal)
//                    Log.d(TAG, "getPortionsOfData: ДАН ПОРЦ: $dataPortion")
                    Log.d(TAG, "getPortionsOfData: ДАН ПОРЦ: ${dataPortion.id}")
                    dataPortions.add(dataPortion)
                }

                Log.d(TAG, "getPortionsOfData: СПИСОК ПОРЦ ДАН: $dataPortions")
                Log.d(TAG, "getPortionsOfData: индексы ")

                withContext(Dispatchers.Main) {
                    dataPortionsLV.value = dataPortions
                }

            }
        }
    }

    fun getPortionsOfDataForTask(unitId: Int, taskId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val indexesOfMutual = db.getUnitTaskDataDao().getListOfIndOfMutual(unitId, taskId)
                Log.d(TAG, "getPortionsOfDataForTask:: инд общн: $indexesOfMutual")

                for (i in indexesOfMutual) {
                    val tempData = db.getUnitTaskDataDao().getMutualTasks(i, taskId, unitId)
                        .map { it.toDomain() }
                    val data = mutableListOf<CourseUnitTaskData>()

                    for (d in tempData) {
                        val n = d.copy(pics = db.getPicturesDao().getPicturesByTasksData(d.id)
                            ?.map { it.picture })
                        data.add(n)
                    }

                    Log.d(TAG, "getPortionsOfDataForTask:: спис общ дан: $data ")
                    Log.d(TAG, "getPortionsOfDataForTask:: инд общ для верх дан: $i ")

                    val portion = DataPortion(i, data)
                    Log.d(TAG, "getPortionsOfDataForTask::порц дан: $portion ")
                    dataPortions.add(portion)

//                    Log.d(TAG, "")
//                    Log.d(TAG, "")
//                    Log.d(TAG, "")
                }

                Log.d(TAG, "getPortionsOfDataForTask::спис порц дан: $dataPortions")
            }
        }
    }

    // Получение деталей блока
    fun getDetailsOfUnit(unitId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = db.getUnitTaskDao().getTasksByUnitId(unitId).map { it.toDomain() }
                withContext(Dispatchers.Main) {
                    _unitTasks.value = list
                }
            }
        }
    }

    // Получение списка индексов для экрана заданий блока
//    private fun getListIndOfMutual(unitId: Int, taskId: Int): List<Int> {
////        viewModelScope.launch {
//
//        return withContext(Dispatchers.IO) {
//            db
//                .getUnitTaskDataDao()
//                .getListOfIndOfMutual(unitId, taskId)
//
//        }
////        }
//    }

    // Получение данных для задания блока
    private fun getPortionOfDataByIdOfMutual(idOfMut: Int, unitId: Int = 0, taskId: Int = 0) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val listOfMutualData =
                    db.getUnitTaskDataDao().getMutualTasks(idOfMut, taskId, unitId)
                        .map { it.toDomain() }.map { data ->
                            data.copy(pics = db.getPicturesDao().getPicturesByTasksData(data.id)
                                ?.map { it.picture })
                        }


                Log.d(TAG, "getPortionOfDataByIdOfMutual: $listOfMutualData")

                val portion = mutableListOf<CourseUnitTaskData>()

                for (p in listOfMutualData) {
                    val n = p.copy(pics = db.getPicturesDao().getPicturesByTasksData(p.id)
                        ?.map { it.picture })
                    portion.add(n)
                }

                withContext(Dispatchers.Main) {
                    _unitTaskDataWPics.value = portion
                }

            }
        }
    }

    private val TAG = "UnitViewModel"

}