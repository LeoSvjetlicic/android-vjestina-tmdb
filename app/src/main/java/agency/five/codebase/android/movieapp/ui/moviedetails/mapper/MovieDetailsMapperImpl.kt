package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        return MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = movieDetails.crew.map { crewman ->
                CrewItemViewState(
                    id=crewman.id,
                    name = crewman.name,
                    job = crewman.job
                )
            },
            cast = movieDetails.cast.map { castItem ->
                ActorCardViewState(
                    id=castItem.id,
                    name = castItem.name,
                    imageUrl = castItem.imageUrl,
                    character = castItem.character
                )
            },
        )
    }
}
