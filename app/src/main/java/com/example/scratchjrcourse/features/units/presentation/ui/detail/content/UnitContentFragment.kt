package com.example.scratchjrcourse.features.units.presentation.ui.detail.content

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scratchjrcourse.databinding.FragmentUnitContentBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitContentAdapter
import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Экран содержания раздела
 */
class UnitContentFragment : Fragment() {

    private val args: UnitContentFragmentArgs by navArgs()
    private lateinit var unitContentAdapter: UnitContentAdapter
    private lateinit var viewBinding: FragmentUnitContentBinding
    private val unitViewModel: UnitViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentUnitContentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvCourseContent.layoutManager = LinearLayoutManager(activity)
        unitViewModel.getDetailsOfUnit(args.unitId)
        unitViewModel.unitTasks.observe(viewLifecycleOwner) {
            // При нажатии на задание блока происходить переход на экран с каруселью данных
            // ТОЛЬКО этого задания
            unitContentAdapter = UnitContentAdapter() { unitId, taskId, isQuest ->
                when (isQuest) {
                    true -> {
                        findNavController().navigate(
                            UnitContentFragmentDirections.actionUnitContentFragmentToTaskQuestionFragment(
                                unitId = unitId, taskId = taskId, isQuest = true
                            )
                        )
                        Log.d(TAG, "onViewCreated: logd")
                    }

                    false -> {
                        findNavController().navigate(
                            UnitContentFragmentDirections.actionUnitContentFragmentToUnitTaskFragment(
                                unitId,
                                taskId
                            )
                        )
                    }
                }

            }
            unitContentAdapter.setTasks(it)
            viewBinding.rvCourseContent.adapter = unitContentAdapter
        }
    }

    companion object {
        private const val TAG = "UnitContentFragment"
    }

}