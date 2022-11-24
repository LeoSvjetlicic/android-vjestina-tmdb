package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.*
import androidx.compose.runtime.*

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()
val movies = MoviesMock.getMoviesList()
val popular = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_FOR_RENT,
    MovieCategory.POPULAR_IN_THEATRES
)
val nowPlaying = listOf(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV
)
val upcoming = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK
)
var popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movies = movies,
    movieCategories = popular, selectedMovieCategory = MovieCategory.POPULAR_STREAMING
)
var nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movies = movies,
    movieCategories = nowPlaying, selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES
)
var upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    movies = movies,
    movieCategories = upcoming, selectedMovieCategory = MovieCategory.UPCOMING_TODAY
)

@Composable
fun HomeRoute(
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val popularCategoryViewState by remember { mutableStateOf(popularCategoryViewState) }
    val nowPlayingCategoryViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    val upcomingCategoryViewState by remember { mutableStateOf(upcomingCategoryViewState) }
    HomeScreen(
        popular = popularCategoryViewState,
        nowPlaying = nowPlayingCategoryViewState,
        upcoming = upcomingCategoryViewState,
        onMovieCardClick = { onMovieCardClick() },
        onFavoriteButtonClick = { onFavoriteButtonClick() },
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HomeScreenPart(
                viewState = popular,
                title = stringResource(id = R.string.whats_popular),
                modifier = Modifier,
                onMovieCardClick = { onMovieCardClick() },
                onFavoriteButtonClick = { onFavoriteButtonClick() })
            HomeScreenPart(
                viewState = nowPlaying,
                title = stringResource(id = R.string.now_playing),
                modifier = Modifier,
                onMovieCardClick = { onMovieCardClick() },
                onFavoriteButtonClick = { onFavoriteButtonClick() })
            HomeScreenPart(
                viewState = upcoming,
                title = stringResource(id = R.string.upcoming),
                modifier = Modifier,
                onMovieCardClick = { onMovieCardClick() },
                onFavoriteButtonClick = { onFavoriteButtonClick() })
        }
    }
}

@Composable
fun HomeScreenPart(
    viewState: HomeMovieCategoryViewState,
    title: String,
    modifier: Modifier,
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
) {
    Column(modifier = modifier.padding(10.dp)) {
        Text(
            text = title,
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
        ) {
            items(
                items = viewState.movieCategories,
                key = { category -> category.itemId }
            ) { it ->
                MovieCategoryLabel(
                    modifier = Modifier.padding(end = 10.dp),
                    labelViewState = it,
                    onClick = { onCategoryClick(it) }
                )
            }
        }
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(
                items = viewState.movies,
                key = { movie -> movie.id }
            ) { item ->
                MovieCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    movie = MovieCardViewState(
                        item.imageUrl,
                        item.isFavorite
                    ),
                    onFavoriteButtonClick = { onFavoriteButtonClick() },
                    onMovieCardClick = { onMovieCardClick() }
                )
            }
        }
    }
}

fun onCategoryClick(it: MovieCategoryLabelViewState) {
    when (it.itemId) {
        0 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = movies,
                    movieCategories = popular,
                    selectedMovieCategory = MovieCategory.POPULAR_STREAMING
                )
        }
        1 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = movies,
                    movieCategories = popular,
                    selectedMovieCategory = MovieCategory.POPULAR_ON_TV
                )
        }
        2 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = movies,
                    movieCategories = popular,
                    selectedMovieCategory = MovieCategory.POPULAR_FOR_RENT
                )
        }
        3 -> {
            popularCategoryViewState =
                homeScreenMapper.toHomeMovieCategoryViewState(
                    movies = movies,
                    movieCategories = popular,
                    selectedMovieCategory = MovieCategory.POPULAR_IN_THEATRES
                )
        }
        4 -> {
            nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = movies,
                movieCategories = nowPlaying,
                selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES
            )
        }
        5 -> {
            nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = movies,
                movieCategories = nowPlaying,
                selectedMovieCategory = MovieCategory.NOW_PLAYING_TV
            )
        }
        6 -> {
            upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = movies,
                movieCategories = upcoming,
                selectedMovieCategory = MovieCategory.UPCOMING_TODAY
            )
        }
        else -> {
            upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                movies = movies,
                movieCategories = upcoming,
                selectedMovieCategory = MovieCategory.UPCOMING_THIS_WEEK
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        popular = popularCategoryViewState,
        nowPlaying = nowPlayingCategoryViewState,
        upcoming = upcomingCategoryViewState,
        onMovieCardClick = { },
        onFavoriteButtonClick = { }
    )
}
