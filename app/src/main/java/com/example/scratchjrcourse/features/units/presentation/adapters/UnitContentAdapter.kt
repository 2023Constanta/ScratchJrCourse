package com.example.scratchjrcourse.features.units.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.ItemCourseUnitTaskBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTask

/**
 * Адаптер содержания блока. Принимает список заданий блока [CourseUnitTask]
 */
class UnitContentAdapter(
    private val onTaskClick: ((id: Int, unitId: Int, isQuestion: Boolean) -> Unit)?
) : RecyclerView.Adapter<UnitContentAdapter.TasksViewHolder>() {

    private var tasksInUnit: MutableList<CourseUnitTask> = mutableListOf()

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

    fun setTasks(tasks: List<CourseUnitTask>) {
        tasksInUnit.clear()
        tasksInUnit.addAll(tasks)
        notifyDataSetChanged()
    }

    inner class TasksViewHolder(private val binding: ItemCourseUnitTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: CourseUnitTask) {
            binding.apply {
                tvUnitTask.setOnClickListener {
                    with(task) {
                        onTaskClick?.invoke(unitId, id, isQuestion)
                    }
                }
                tvUnitTask.text = task.name
            }
        }
    }

}