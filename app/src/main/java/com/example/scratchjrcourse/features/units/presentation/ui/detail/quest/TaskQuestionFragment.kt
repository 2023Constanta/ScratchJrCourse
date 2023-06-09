package com.example.scratchjrcourse.features.units.presentation.ui.detail.quest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.scratchjrcourse.databinding.FragmentTaskQuestionBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.CourseUnitTaskData
import com.example.scratchjrcourse.features.units.presentation.adapters.TaskQuestionsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class TaskQuestionFragment : Fragment() {

    private val taskQuestionsAdapter = TaskQuestionsAdapter()
    private val args: TaskQuestionFragmentArgs by navArgs()
    private lateinit var binding: FragmentTaskQuestionBinding
    private val questionsViewModel: TaskQuestionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskQuestionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionsViewModel.getQuestions(args.unitId, args.taskId, true)
        questionsViewModel.taskQuestions.observe(viewLifecycleOwner, ::observeQuestions)

        with(binding) {
            with(rvUnitQuestions) {
                layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(rvUnitQuestions)
                adapter = taskQuestionsAdapter
            }

        }

    }

    private fun observeQuestions(courseUnitTaskData: List<CourseUnitTaskData>) {
        Log.d(TAG, "observeQuestions: $courseUnitTaskData")
        taskQuestionsAdapter.setQuest(courseUnitTaskData)
    }

    private val TAG = "TaskQuestFrag"


}