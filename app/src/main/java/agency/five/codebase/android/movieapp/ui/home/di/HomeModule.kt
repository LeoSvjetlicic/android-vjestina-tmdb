package agency.five.codebase.android.movieapp.ui.home.di

import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            movieRepository = get(),
            homeScreenMapper = get()
        )
    }
    single<HomeScreenMapper> { HomeScreenMapperImpl() }
}
