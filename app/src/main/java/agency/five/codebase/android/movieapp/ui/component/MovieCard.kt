package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class MovieDetails(
    val movie: Movie,
)
@Composable
fun Movie(
    movie: Movie,
    modifier: Modifier = Modifier
){
    Card(modifier = Modifier
        .width(140.dp)
        .height(200.dp)
        .padding(10.dp)
        .clickable{},
        shape= RoundedCornerShape(10.dp)
    ){
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
        )
        FavoriteButton()

    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie =MoviesMock.getMovieDetails().movie
    Movie(movie = movie)
}