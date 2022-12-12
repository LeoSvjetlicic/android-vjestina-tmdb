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
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import androidx.compose.runtime.*
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel

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
    homeViewModel: HomeViewModel = viewModel(),
    onNavigateToMovieDetails: (Int) -> Unit
) {
    val popularCategoryViewState: HomeMovieCategoryViewState by homeViewModel.popularCategoryViewState.collectAsState()
    val nowPlayingCategoryViewState: HomeMovieCategoryViewState by homeViewModel.nowPlayingCategoryViewState.collectAsState()
    val upcomingCategoryViewState: HomeMovieCategoryViewState by homeViewModel.upcomingCategoryViewState.collectAsState()
    HomeScreen(
        popularCategoryViewState, nowPlayingCategoryViewState, upcomingCategoryViewState,
        onCategoryClick = { category ->
            homeViewModel.changeCategory(category.itemId)
        },
        onFavoriteButtonClick = { movie ->
            homeViewModel.toggleFavorite(movie)
        },
        onMovieCardClick = onNavigateToMovieDetails
    )
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    onMovieCardClick: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HomeScreenPart(
                viewState = popular,
                title = stringResource(id = R.string.whats_popular),
                modifier = Modifier,
                onMovieCardClick = onMovieCardClick,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onCategoryClick = onCategoryClick
            )
            HomeScreenPart(
                viewState = nowPlaying,
                title = stringResource(id = R.string.now_playing),
                modifier = Modifier,
                onMovieCardClick = onMovieCardClick,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onCategoryClick = onCategoryClick
            )
            HomeScreenPart(
                viewState = upcoming,
                title = stringResource(id = R.string.upcoming),
                modifier = Modifier,
                onMovieCardClick = onMovieCardClick,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
fun HomeScreenPart(
    viewState: HomeMovieCategoryViewState,
    title: String,
    modifier: Modifier,
    onMovieCardClick: (Int) -> Unit,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
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
            ) {
                MovieCategoryLabel(
                    modifier = Modifier.padding(end = 10.dp),
                    labelViewState = it,
                    onClick = { onCategoryClick(it) }
                )
            }
        }
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items = viewState.movies.movieCardViewStates, key = { movie ->
                movie.id
            }) { movie ->
                MovieCard(
                    movie = MovieCardViewState(
                        movie.movieViewState.imageUrl, movie.movieViewState.isFavorite
                    ),
                    onFavoriteButtonClick = { onFavoriteButtonClick(movie.id) },
                    onMovieCardClick = { onMovieCardClick(movie.id) },
                )
            }
        }
    }
}

