package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Crewman
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = crewItemViewState.name,
            modifier = Modifier
                .padding(start = 5.dp, end = 30.dp, top = 5.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = crewItemViewState.job,
            modifier = Modifier
                .padding(start = 5.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
            fontSize = 13.sp,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun CrewItemPreview() {
    val crewMan = MoviesMock.getCrewman()
    CrewItem(crewItemViewState = CrewItemViewState(crewMan.name, crewMan.job))
}
