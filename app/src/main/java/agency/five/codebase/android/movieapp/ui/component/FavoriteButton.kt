package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import okhttp3.internal.notify

@Composable
fun FavoriteButton(
    modifier: Modifier,
    isFavorite: Boolean,
    onFavoriteButtonClick: () -> Unit,
) {
    Image(
        painter = painterResource(
            id = if (isFavorite) R.drawable.full_favorite else R.drawable.empty_favorite
        ),
        contentDescription = null,
        modifier = modifier
            .size(40.dp)
            .background(Color.Gray.copy(0.7f), CircleShape)
            .padding(5.dp)
            .clickable { onFavoriteButtonClick() },
        contentScale = ContentScale.Fit,
    )
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    var isFavorite = remember {
        mutableStateOf(false)
    }
    FavoriteButton(
        modifier = Modifier,
        onFavoriteButtonClick = { isFavorite.value = isFavorite.value.not() },
        isFavorite = isFavorite.value,
    )

}
