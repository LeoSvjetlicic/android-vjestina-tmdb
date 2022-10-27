package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier
) {
    var isFavorite by rememberSaveable { mutableStateOf(true) }
    Box{
        Surface(
            color = Color.Gray,
            shape= CircleShape,
            modifier = Modifier
                .alpha(alpha=0.7f)
        ){
        Image(
            painter = painterResource(
                id = if (isFavorite) R.drawable.full_favorite else R.drawable.empty_favorite
            ),
            contentDescription = null,
            modifier = Modifier
                .clickable { isFavorite = !isFavorite }
                .size(32.dp)
                .padding(5.dp),
            contentScale = ContentScale.Fit,
        )
        }
    }
    modifier.size(32.dp)
}




@Preview
@Composable
private fun FavoriteButtonPreview() {
    FavoriteButton()
}