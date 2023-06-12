package com.example.scratchjrcourse.features.units.presentation.ui.detail.carousel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.scratchjrcourse.databinding.FragmentUnitTaskBinding
import com.example.scratchjrcourse.features.units.domain.domain.model.DataPortion
import com.example.scratchjrcourse.features.units.presentation.adapters.UnitTaskCarouselAdapter
import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * Экран-карусель. Показывает экраны, на которых показываются порции данных
 */
class UnitTaskCarouselFragment : Fragment() {

    private val args: UnitTaskCarouselFragmentArgs by navArgs()
    private var binding: FragmentUnitTaskBinding? = null
    private val unitViewModel: UnitViewModel by sharedViewModel()
    private lateinit var adapter: UnitTaskCarouselAdapter

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

        unitViewModel.dataPortions.observe(viewLifecycleOwner, ::observePortions)

        // Получение порций данных для экранчиков
        unitViewModel.getPortionsOfData(args.unitId, args.taskId)

//        binding?.wpCourseUnitTasks?.registerOnPageChangeCallback(object :
//            ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                if (position == 0) {
//                    binding?.btnPrevUnit!!.visibility = View.GONE
//
//                } else if (position ==
//                    (binding?.wpCourseUnitTasks?.adapter as UnitTaskCarouselAdapter).itemCount
//                ) {
//                    binding?.btnNextUnit?.visibility = View.GONE
//                }
//            }
//        }
//        )

        binding?.btnNextUnit?.setOnClickListener {
            var currPage = binding?.wpCourseUnitTasks?.currentItem
            var countOfPages =
                (binding?.wpCourseUnitTasks?.adapter as UnitTaskCarouselAdapter).itemCount

            var nextPage = currPage?.plus(1)
            if (nextPage != null) {
                if (nextPage >= countOfPages) {
                    nextPage = 0
                }
            }
            if (nextPage != null) {
                binding?.wpCourseUnitTasks?.setCurrentItem(nextPage, true)
            }
        }

        binding?.btnPrevUnit?.setOnClickListener {
            var currPage = binding?.wpCourseUnitTasks?.currentItem
            var countOfPages =
                (binding?.wpCourseUnitTasks?.adapter as UnitTaskCarouselAdapter).itemCount

            var prevPage = currPage?.minus(1)
            if (prevPage != null) {
                if (prevPage < 0) {
                    prevPage = countOfPages - 1
                }
            }
            if (prevPage != null) {
                binding?.wpCourseUnitTasks!!.setCurrentItem(prevPage, true)
            }
        }
    }

    private fun observePortions(dataPortions: List<DataPortion>) {
        adapter = UnitTaskCarouselAdapter()
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