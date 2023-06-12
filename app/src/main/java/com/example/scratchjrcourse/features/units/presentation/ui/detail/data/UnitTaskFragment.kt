package com.example.scratchjrcourse.features.units.presentation.ui.detail.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.scratchjrcourse.databinding.FragmentUnitTaskBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.DataPortion
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTaskPortionAdapter
import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * Экран-карусель. Показывает экраны, на которых показываются порции данных
 */
class UnitTaskFragment : Fragment() {

    private val args: UnitTaskFragmentArgs by navArgs()
    private var binding: FragmentUnitTaskBinding? = null
    private val unitViewModel: UnitViewModel by sharedViewModel()
    private lateinit var adapter: UnitTaskPortionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnitTaskBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "args: ${args.unitId}, ${args.taskId}")

        unitViewModel.dataPortionsLV.observe(viewLifecycleOwner, ::observePortions)

        // Получение порций данных для экранчиков
        unitViewModel.getPortionsOfData(args.unitId, args.taskId)

//        unitViewModel.getPortionsOfDataForTask(unitId = args.unitId, taskId = args.taskId)


//        adapter = UnitTaskPortionAdapter()
//        binding?.wpCourseUnitTasks?.adapter = adapter
//        adapter.setData(unitViewModel.dataPortions)

//        binding?.btnNextUnit?.setOnClickListener {
//            var currPage = binding?.wpCourseUnitTasks?.currentItem
//            var countOfPages =
//                (binding?.wpCourseUnitTasks?.adapter as UnitTaskCarouselAdapter).itemCount
//
//            var nextPage = currPage?.plus(1)
//            if (nextPage != null) {
//                if (nextPage >= countOfPages) {
//                    nextPage = 0
//                }
//            }
//            if (nextPage != null) {
//                binding?.wpCourseUnitTasks?.setCurrentItem(nextPage, true)
//            }
//        }

//        binding?.btnPrevUnit?.setOnClickListener {
//            var currPage = binding?.wpCourseUnitTasks?.currentItem
//            var countOfPages =
//                (binding?.wpCourseUnitTasks?.adapter as UnitTaskCarouselAdapter).itemCount
//
//            var prevPage = currPage?.minus(1)
//            if (prevPage != null) {
//                if (prevPage < 0) {
//                    prevPage = countOfPages - 1
//                }
//            }
//            if (prevPage != null) {
//                binding?.wpCourseUnitTasks!!.setCurrentItem(prevPage, true)
//            }
//        }
    }

    private fun observePortions(dataPortions: List<DataPortion>) {
        adapter = UnitTaskPortionAdapter()
        adapter.setData(dataPortions)
        binding?.wpCourseUnitTasks?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val TAG = "UnitTaskFragment"
    }


}