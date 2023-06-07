package com.example.scratchjrcourse.features.units.di

import com.example.scratchjrcourse.features.units.presentation.ui.detail.UnitViewModel
import com.example.scratchjrcourse.features.units.presentation.ui.list.UnitsListFragment
import com.example.scratchjrcourse.features.units.presentation.ui.list.UnitsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        UnitsListViewModel(
            get()
        )
    }

    viewModel {
        UnitViewModel(
            get()
        )
    }
}