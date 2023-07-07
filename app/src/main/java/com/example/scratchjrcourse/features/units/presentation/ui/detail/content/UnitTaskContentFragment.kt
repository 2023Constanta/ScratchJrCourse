package com.example.scratchjrcourse.features.units.presentation.ui.detail.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scratchjrcourse.databinding.FragmentUnitContentBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTaskContentAdapter
import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Экран содержания раздела
 */
class UnitTaskContentFragment : Fragment() {

    private val args: UnitTaskContentFragmentArgs by navArgs()
    private lateinit var unitTaskContentAdapter: UnitTaskContentAdapter
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
            unitTaskContentAdapter = UnitTaskContentAdapter() { unitId, taskId, isQuest ->
                when (isQuest) {
                    true -> {
                        findNavController().navigate(
                            UnitTaskContentFragmentDirections.actionUnitContentFragmentToTaskQuestionFragment(
                                unitId = unitId, taskId = taskId, isQuest = true
                            )
                        )
                    }

                    false -> {
                        findNavController().navigate(
                            UnitTaskContentFragmentDirections.actionUnitContentFragmentToUnitTaskFragment(
                                unitId,
                                taskId
                            )
                        )
                    }
                }

            }
            unitTaskContentAdapter.setTasks(it)
            viewBinding.rvCourseContent.adapter = unitTaskContentAdapter
        }
    }

    companion object {
        private const val TAG = "UnitContentFragment"
    }

}