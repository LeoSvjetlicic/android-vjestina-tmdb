package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val id: Int,
    val imageUrl: String?,
    val isFavorite: Boolean
)

@Composable
fun MovieCard(
    movie: MovieCardViewState,
    onMovieCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable { onMovieCardClick() },
        shape = RoundedCornerShape(10.dp)
    ) {
        Box() {
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            FavoriteButton(
                isFavorite = movie.isFavorite,
                modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp),
                onFavoriteButtonClick = onFavoriteButtonClick
            )
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movie = MoviesMock.getMovieDetails().movie
    val movieCardViewState = MovieCardViewState(
        imageUrl = movie.imageUrl,
        isFavorite = movie.isFavorite,
        id = movie.id
    )
    MovieCard(movie = movieCardViewState, onMovieCardClick = {}, onFavoriteButtonClick = {})
}
