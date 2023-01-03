package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    private val favoritesMapper: FavoritesMapper,
) : ViewModel() {
    private val _favoritesViewState = MutableStateFlow(FavoritesViewState())
    val favoritesViewState = _favoritesViewState.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.favoriteMovies().collect {
                _favoritesViewState.value = favoritesMapper.toFavoritesViewState(it)
            }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}
