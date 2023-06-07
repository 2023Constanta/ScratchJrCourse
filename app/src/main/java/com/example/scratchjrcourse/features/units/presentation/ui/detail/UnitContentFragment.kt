package com.example.scratchjrcourse.features.units.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scratchjrcourse.databinding.FragmentUnitContentBinding
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTasksAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Экран содержания раздела
 */
class UnitContentFragment : Fragment() {

    private val args: UnitContentFragmentArgs by navArgs()
    private lateinit var unitContentAdapter: UnitTasksAdapter
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

        unitViewModel.getDetails(args.unitId)

        Log.d(TAG, "onCreate: $unitViewModel")

        unitViewModel.unitTasks.observe(viewLifecycleOwner) {
            viewBinding.rvCourseContent.layoutManager = LinearLayoutManager(activity)
            unitContentAdapter = UnitTasksAdapter(it) { id, unitId ->
                findNavController().navigate(
                    UnitContentFragmentDirections.actionUnitContentFragmentToUnitTaskFragment(
                        id,
                        unitId
                    )
                )
//                Log.d("UnitContent", "onViewCreated: $id $unitId")
            }
            viewBinding.rvCourseContent.adapter = unitContentAdapter
        }
    }

    companion object {
        private val TAG = this::class.java.simpleName
    }

}