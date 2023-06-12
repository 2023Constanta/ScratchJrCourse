package com.example.scratchjrcourse.features.units.presentation.ui.detail.quest

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scratchjrcourse.features.units.data.room.AppDb
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData
import com.example.scratchjrcourse.features.units.domain.domain.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskQuestionViewModel(
    private val db: AppDb
) : ViewModel() {

    private val _taskQuestions = MutableLiveData<List<CourseUnitTaskData>>()
    val taskQuestions get() = _taskQuestions

    fun getQuestions(unitId: Int, taskId: Int, isQuestion: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val questions = db.getUnitTaskDataDao().getQuestions(unitId, taskId, isQuestion)
                    .map { it.toDomain() }

                val ready = mutableListOf<CourseUnitTaskData>()

                for (q in questions) {
                    val n = q.copy(
                        pics = db.getPicturesDao().getPicturesByTasksData(q.id)
                            ?.map { it.picture }
                    )
                    ready.add(n)
                }
                withContext(Dispatchers.Main) {
                    _taskQuestions.value = ready
                }
            }
        }

    }
}