package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState

data class FavoritesMovieViewState(
    val id: Int,
    val movieViewState: MovieCardViewState
)

data class FavoritesViewState(
    val movieCardViewStates: List<FavoritesMovieViewState> = emptyList()
)
