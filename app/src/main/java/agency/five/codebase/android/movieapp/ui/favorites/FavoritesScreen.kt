package agency.five.codebase.android.movieapp.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.theme.LocalSpacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoritesRoute(
    favoritesViewModel: FavoritesViewModel = viewModel(),
    onNavigateToMovieDetails: (Int) -> Unit
) {
    val favoritesViewState: FavoritesViewState by favoritesViewModel.favoritesViewState.collectAsState()
    FavoritesScreen(
        favoritesViewState,
        onNavigateToMovieDetails,
        { movie ->
            favoritesViewModel.toggleFavorite(movie)
        },
    )
}

@Composable
private fun MoviesList(
    movieItems: FavoritesViewState,
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = LocalSpacing.current.extraSmall),
        verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.extraSmall),
        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.extraSmall)
    ) {
        items(movieItems.movieCardViewStates) {
            MovieCard(
                movie = it.movieViewState,
                onFavoriteButtonClick = { onFavoriteButtonClick(it.id) },
                onMovieCardClick = { onNavigateToMovieDetails(it.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun FavoritesScreen(
    favoritesViewState: FavoritesViewState,
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small, LocalSpacing.current.medium)
    ) {
        Text(text = "Favorites", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        MoviesList(
            movieItems = favoritesViewState,
            onNavigateToMovieDetails,
            onFavoriteButtonClick
        )
    }
}

