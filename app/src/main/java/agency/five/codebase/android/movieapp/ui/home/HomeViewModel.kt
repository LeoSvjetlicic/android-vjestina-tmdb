package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val STOP_TIMEOUT_MILIS = 5000L

class HomeScreenViewModel(
    private val movieRepository: MovieRepository,
    private val homeMapper: HomeScreenMapper
) : ViewModel() {

    private val popular = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES
    )
    private val nowPlaying = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV
    )
    private val upcoming = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    )

    private val popularSelected = MutableStateFlow(MovieCategory.POPULAR_STREAMING)
    private val nowPlayingSelected = MutableStateFlow(MovieCategory.NOW_PLAYING_MOVIES)
    private val upcomingSelected = MutableStateFlow(MovieCategory.UPCOMING_TODAY)

    private val initialHomeMovieCategoryViewState =
        HomeMovieCategoryViewState(emptyList(), emptyList())

    val popularCategoryViewState = popularSelected.flatMapLatest { category ->
        movieRepository.movies(category).map { movies ->
            homeMapper.toHomeMovieCategoryViewState(popular, category, movies)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = initialHomeMovieCategoryViewState
    )

    val nowPlayingCategoryViewState = nowPlayingSelected.flatMapLatest { category ->
        movieRepository.movies(category).map { movies ->
            homeMapper.toHomeMovieCategoryViewState(nowPlaying, category, movies)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = initialHomeMovieCategoryViewState
    )

    val upcomingCategoryViewState = upcomingSelected.flatMapLatest { category ->
        movieRepository.movies(category).map { movies ->
            homeMapper.toHomeMovieCategoryViewState(upcoming, category, movies)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILIS),
        initialValue = initialHomeMovieCategoryViewState
    )

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

    fun switchSelectedCategory(categoryId: Int) {
        when (categoryId) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_IN_THEATRES.ordinal
            -> {
                popularSelected.update { MovieCategory.values()[categoryId] }
            }
            MovieCategory.NOW_PLAYING_MOVIES.ordinal,
            MovieCategory.NOW_PLAYING_TV.ordinal
            -> {
                nowPlayingSelected.update { MovieCategory.values()[categoryId] }
            }
            else -> {
                upcomingSelected.update { MovieCategory.values()[categoryId] }
            }
        }
    }
}
