package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    labelViewState: MovieCategoryLabelViewState
) {
    if (labelViewState.isSelected) {
        Selected(labelViewState)
    } else {
        NotSelected(labelViewState)
    }
}
@Composable
fun Selected(labelViewState: MovieCategoryLabelViewState) {
    Column {
        Text(
            text = getTextSource(labelViewState = labelViewState),
            fontSize = 16.sp,
        )
        Divider(thickness = 3.dp, color = Color.Black)
    }
}

@Composable
fun NotSelected(labelViewState: MovieCategoryLabelViewState) {
    Text(
        text = getTextSource(labelViewState = labelViewState),
        fontSize = 16.sp,
        color = Color.Gray
    )
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

    val textFromString = MovieCategoryLabelTextViewState.StringToLabelText("Movies")
    val selected = MovieCategoryLabelViewState(1, true, textFromString)
    val unselected = MovieCategoryLabelViewState(2, false, textFromString)

    Surface (modifier = Modifier.size(150.dp)){
        Column() {
            MovieCategoryLabel(labelViewState = selected);
            MovieCategoryLabel(labelViewState = unselected)
        }
    }
}