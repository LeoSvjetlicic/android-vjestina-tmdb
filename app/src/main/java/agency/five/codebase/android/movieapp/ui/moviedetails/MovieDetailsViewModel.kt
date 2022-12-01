package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieId: Int
) : ViewModel() {
    private val _movieDetailsViewState = MutableStateFlow(
        MovieDetailsViewState(
            id = 1,
            imageUrl = null,
            voteAverage = 8.1f,
            title = "",
            overview = "",
            isFavorite = false,
            crew = emptyList(),
            cast = emptyList()
        )
    )
    val movieDetailsViewState = _movieDetailsViewState.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.movieDetails(_movieDetailsViewState.value.id).collect { details ->
                _movieDetailsViewState.value = movieDetailsMapper.toMovieDetailsViewState(details)
            }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
            viewModelScope.launch {
                movieRepository.movieDetails(_movieDetailsViewState.value.id).collect { details ->
                    _movieDetailsViewState.value =
                        movieDetailsMapper.toMovieDetailsViewState(details)
                }
            }
        }
    }
}
