package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScoreProgressBar(
    modifier: Modifier = Modifier,
    score: Float
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize(0.93f)) {
            drawCircle(
                color = Color(red = 120, green = 150, blue = 50),
                style = Stroke(
                    width = 8f,
                )
            )
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360 * (0.1f * score),
                useCenter = false,
                style = Stroke(
                    width = 8f,
                    cap = StrokeCap.Round
                )
            )
        }
        Text(text = score.toString(), color = Color.Black, fontSize = 15.sp)
    }
}

@Preview()
@Composable
fun UserScoreProgressBarPreview() {
    UserScoreProgressBar(score = 7.5f)
}
