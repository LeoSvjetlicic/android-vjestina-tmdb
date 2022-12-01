package agency.five.codebase.android.movieapp.ui.moviedetails.di

import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel {
        MovieDetailsViewModel(
            movieRepository = get(),
            movieDetailsMapper = get(),
            movieId = it.get()
        )
    }
    single<MovieDetailsMapper> { MovieDetailsMapperImpl() }
}
