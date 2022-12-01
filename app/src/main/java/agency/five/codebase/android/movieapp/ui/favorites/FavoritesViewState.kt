package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState

data class FavoritesViewState (
    val movieCardViewStates: List<MovieCardViewState> = listOf()
)
