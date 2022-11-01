package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieViewState(
    val title: String,
    val overview: String,
    val imageUrl: String?,
    var isFavorite: MutableState<Boolean>
)

@Composable
fun Movie(
    movie: MovieViewState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable { onClick },
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
        )
        FavoriteButton(
            isFavorite = movie.isFavorite.value,
            modifier = Modifier.padding(5.dp)
        ) {
            movie.isFavorite.value = it
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    val movie = MoviesMock.getMovieDetails().movie
    val movieViewState = MovieViewState(
        title = movie.title,
        overview = movie.overview,
        imageUrl = movie.imageUrl,
        isFavorite = remember {
            mutableStateOf(movie.isFavorite)
        },
    )
    Movie(movie = movieViewState, onClick = {})
}
