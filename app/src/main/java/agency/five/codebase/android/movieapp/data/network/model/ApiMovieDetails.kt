package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("original_language")
    val language: String,
    @SerialName("runtime")
    val runtime: Int?,
) {
    fun toMovieDetails(isFavorite: Boolean, crew: List<Crewman>, cast: List<Actor>) = MovieDetails(
        movie = toMovie(isFavorite),
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        language = language,
        runtime = runtime ?: 0,
        crew = crew,
        cast = cast
    )
    private fun toMovie(isFavorite: Boolean) = Movie(
        id = id,
        title = title,
        overview = overview,
        imageUrl = "$BASE_IMAGE_URL/$posterPath",
        isFavorite = isFavorite
    )
}
