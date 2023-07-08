package com.example.hadithcollection

import com.example.hadithcollection.ViewModel.TirmidhiViewModel
import dagger.Component



@Component
interface AppComponent {
    fun inject(viewModel: TirmidhiViewModel)
}