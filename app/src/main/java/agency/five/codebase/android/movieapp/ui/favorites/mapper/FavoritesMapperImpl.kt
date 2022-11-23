package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    private var favoritesMovieViewState = mutableListOf<FavoritesMovieViewState>()
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        for (movie in favoriteMovies) {
            var movieCardViewState = MovieCardViewState(movie.imageUrl, movie.isFavorite)
            if (movieCardViewState.isFavorite) {
                favoritesMovieViewState.add(FavoritesMovieViewState(movie.id, movieCardViewState))
            }
        }
        return FavoritesViewState(favoritesMovieViewState)
    }
}
