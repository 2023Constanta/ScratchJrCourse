package com.example.scratchjrcourse.features.units.presentation.adapters

import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.scratchjrcourse.R
import com.example.scratchjrcourse.databinding.ItemTaskQuestionBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData

/**
 * Адаптер, который держит вопросы
 */
class TaskQuestionsAdapter : RecyclerView.Adapter<TaskQuestionsAdapter.ViewHolder>() {

    private var data: MutableList<CourseUnitTaskData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemTaskQuestionBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_task_question, parent, false),
        )
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setQuest(list: List<CourseUnitTaskData>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemTaskQuestionBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CourseUnitTaskData) {
            with(binding) {
                ivQuestionPic.load(data.pics?.get(0)) {
                    scale(Scale.FIT)
                }
                tvQuestionText.text = data.text

                etQuestionAnswer.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        val text = etQuestionAnswer.text.trim().toString()
                        Toast.makeText(
                            binding.root.context,
                            "Ваш ответ: \"$text\"!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    true
                }

            }
        }

    }
}