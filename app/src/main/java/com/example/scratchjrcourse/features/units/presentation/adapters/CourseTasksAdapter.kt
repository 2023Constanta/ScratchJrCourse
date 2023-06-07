package com.example.scratchjrcourse.features.units.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.ItemCourseUnitBinding
import com.example.scratchjrcourse.databinding.ItemCourseUnitTaskBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnit
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask

/**
 * Адаптер заданий открытого курса. Принимает список разделов [CourseUnit]
 */
class CourseTaskAdapter(
    private val units: List<CourseUnit>,
//    private val onTaskClick: ((courseId: Int, unitId: Int, taskId: Int) -> Unit)
    private val onTaskClick: ((id: Int, unitId: Int) -> Unit)?
) : RecyclerView.Adapter<CourseTaskAdapter.ViewHolder>() {

    private lateinit var adapter: UnitTasksAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCourseUnitBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_course_unit, parent, false)
        )
    )

    override fun getItemCount(): Int = units.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(
            position,
            unitTitle = units[position].name.toString(),
            tasksList = listOf()
        )

    inner class ViewHolder(private val binding: ItemCourseUnitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, unitTitle: String, tasksList: List<CourseUnitTask>) {
            binding.apply {
                btnExpandUnits.text = unitTitle
                btnExpandUnits.setOnClickListener {
                   val vis = lessonsList.isVisible

                   lessonsList.isVisible = !vis
                }

                adapter = UnitTasksAdapter(
                    tasksInUnit = tasksList,
                    onTaskClick = onTaskClick
                )
                lessonsList.adapter = adapter

            }

        }
    }

}

/**
 * Принимает список заданий раздела [CourseUnitTask]
 */
class UnitTasksAdapter(
    private val tasksInUnit: List<CourseUnitTask>,
    private val onTaskClick: ((id: Int, unitId: Int) -> Unit)?
) : RecyclerView.Adapter<UnitTasksAdapter.TasksViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder =
        TasksViewHolder(
            ItemCourseUnitTaskBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_course_unit_task, parent, false)
            )
        )


    override fun getItemCount(): Int = tasksInUnit.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasksInUnit[position])
    }

    inner class TasksViewHolder(private val binding: ItemCourseUnitTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: CourseUnitTask) {
            binding.apply {
                tvUnitTask.setOnClickListener {
                    with(task) {
                        onTaskClick?.invoke(id, unitId)
                    }
                }
                tvUnitTask.text = task.name ?: "BOO!"
            }
        }
    }


}