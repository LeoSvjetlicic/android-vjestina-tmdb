package agency.five.codebase.android.movieapp.ui.favorites.di

import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel {
        FavoritesViewModel(
            movieRepository = get(),
            favoritesMapper = get()
        )
    }
    single<FavoritesMapper> { FavoritesMapperImpl() }
}
