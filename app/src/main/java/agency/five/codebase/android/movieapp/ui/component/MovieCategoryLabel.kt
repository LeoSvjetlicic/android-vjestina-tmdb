package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import okhttp3.internal.notify

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState {
    class StringToLabelText(val text: String) : MovieCategoryLabelTextViewState()
    class ResourceToLabelText(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    labelViewState: MovieCategoryLabelViewState,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier.clickable { onClick() }) {
        if (labelViewState.isSelected) {
            Text(
                modifier = Modifier,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                text = getTextSource(labelViewState = labelViewState),
                fontSize = 16.sp,

                )
        } else {
            Text(
                modifier = Modifier,
                text = getTextSource(labelViewState = labelViewState),
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun getTextSource(labelViewState: MovieCategoryLabelViewState): String {
    return when (val categoryText = labelViewState.categoryText) {
        is MovieCategoryLabelTextViewState.StringToLabelText ->
            categoryText.text
        is MovieCategoryLabelTextViewState.ResourceToLabelText ->
            stringResource(id = categoryText.textRes)
    }
}

@Preview
@Composable
private fun MovieCategoryLabelPreview() {
    var isSelected = remember {
        mutableStateOf(false)
    }
    val textFromString = MovieCategoryLabelTextViewState.StringToLabelText("Movies")
    val labelViewState =
        MovieCategoryLabelViewState(1, false, textFromString)

    MovieCategoryLabel(
        labelViewState = labelViewState,
        modifier = Modifier,
        onClick = {}
    )
}
