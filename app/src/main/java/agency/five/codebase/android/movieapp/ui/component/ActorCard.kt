package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Actor
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.Placeholder
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter

data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = null,
                modifier = Modifier.height(125.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = actorCardViewState.name,
                modifier = Modifier
                    .padding(start = 10.dp, end = 30.dp, top = 5.dp),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = actorCardViewState.character,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                fontSize = 8.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()
    val modifier = Modifier
        .width(140.dp)
        .height(200.dp)
    ActorCard(
        actorCardViewState = ActorCardViewState(actor.imageUrl, actor.name, actor.character),
        modifier
    )
}

