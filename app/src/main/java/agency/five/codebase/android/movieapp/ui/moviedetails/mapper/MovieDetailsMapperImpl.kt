package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState

import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        var crew = mutableListOf<CrewItemViewState>()
        var cast = mutableListOf<ActorCardViewState>()
        for (crewItem in movieDetails.crew) {
            crew.add(CrewItemViewState(name = crewItem.name, job = crewItem.job))
        }
        for (castItem in movieDetails.cast) {
            cast.add(
                ActorCardViewState(
                    imageUrl = castItem.imageUrl,
                    name = castItem.name,
                    character = castItem.character
                )
            )
        }
        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = crew,
            cast = cast,
        )
    }
}
