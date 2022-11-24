package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onFavoriteButtonClick: () -> Unit,
    onMovieCardClick: () -> Unit
) {
    val favorites by remember { mutableStateOf(favoritesViewState) }
    FavoritesScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        favoritesViewState = favorites,
        onFavoriteButtonClick = { onFavoriteButtonClick() },
        onMovieCardClick = { onMovieCardClick() }
    )
}

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState,
    onFavoriteButtonClick: () -> Unit,
    onMovieCardClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.favorites_text),
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                items(
                    items = favoritesViewState.favoriteMovies,
                    key = { movie -> movie.id }) { card ->
                    MovieCard(
                        modifier = Modifier
                            .width(120.dp)
                            .height(180.dp),
                        movie = card.movieViewState,
                        onFavoriteButtonClick = onFavoriteButtonClick,
                        onMovieCardClick = onMovieCardClick
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState = favoritesViewState,
            modifier = Modifier,
            onFavoriteButtonClick = {},
            onMovieCardClick = {},
        )
    }
}
