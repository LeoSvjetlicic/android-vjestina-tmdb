package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.mock.FavoritesDBMock
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class FakeMovieRepository(
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {
    private val fakeMovies = MoviesMock.getMoviesList().toMutableList()
    private val movies: Flow<List<Movie>> = FavoritesDBMock.favoriteIds
        .mapLatest { favoriteIds ->
            fakeMovies.map { movie ->
                movie.copy(isFavorite = favoriteIds.contains(movie.id))
            }
        }
        .flowOn(ioDispatcher)

    override fun popularMovies(movieCategory: MovieCategory) = movies

    override fun nowPlayingMovies(movieCategory: MovieCategory) = movies

    override fun upcomingMovies(movieCategory: MovieCategory) = movies

    override fun movieDetails(movieId: Int): Flow<MovieDetails> =
        FavoritesDBMock.favoriteIds
            .mapLatest { favoriteIds ->
                val movieDetails = MoviesMock.getMovieDetails(movieId)
                val tempMovie =
                    movieDetails.movie.copy(isFavorite = favoriteIds.contains(movieDetails.movie.id))
                tempMovie.copy(isFavorite = favoriteIds.contains(tempMovie.id))
                movieDetails.copy(movie = tempMovie)
            }
            .flowOn(ioDispatcher)

    override fun favoriteMovies(): Flow<List<Movie>> = movies.map {
        it.filter { fakeMovie -> fakeMovie.isFavorite }
    }

    override suspend fun addMovieToFavorites(movieId: Int) {
        FavoritesDBMock.insert(movieId)
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        FavoritesDBMock.delete(movieId)
    }

    override suspend fun toggleFavorite(movieId: Int) =
        if (FavoritesDBMock.favoriteIds.value.contains(movieId)) {
            removeMovieFromFavorites(movieId)
        } else {
            addMovieToFavorites(movieId)
        }

}
