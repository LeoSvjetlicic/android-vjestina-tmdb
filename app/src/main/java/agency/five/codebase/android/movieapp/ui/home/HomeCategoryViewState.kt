package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

data class HomeMovieViewState(
    val id: Int,
    val movieViewState: MovieCardViewState
)

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState> = listOf(),
    val movies: List<HomeMovieViewState>
)
