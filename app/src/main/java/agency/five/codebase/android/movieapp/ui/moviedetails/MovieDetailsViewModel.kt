package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import android.icu.text.UnicodeSet.EMPTY
import android.net.Uri.EMPTY
import android.os.Bundle.EMPTY
import android.os.PersistableBundle.EMPTY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieId: Int
) : ViewModel() {
    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        movieRepository.movieDetails(movieId)
            .map { movies -> movieDetailsMapper.toMovieDetailsViewState(movies) }
            .stateIn(
                viewModelScope, SharingStarted.Eagerly, movieDetailsMapper.toMovieDetailsViewState(
                    MoviesMock.getMovieDetails(movieId)
                )
            )

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
