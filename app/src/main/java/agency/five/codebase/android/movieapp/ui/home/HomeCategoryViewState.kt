package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

data class HomeMovieViewState(
    val id: Int,
    val imageUrl: String?,
    val isFavorite: Boolean,
)

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState> = listOf(),
    val movies: FavoritesViewState = FavoritesViewState()
)
