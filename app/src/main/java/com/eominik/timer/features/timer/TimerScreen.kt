package com.eominik.timer.features.timer

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.eominik.timer.core.theme.TimerTheme

@Composable
fun TimerScreen(navController: NavHostController) {
    ScreenContent()
}

@Composable
private fun ScreenContent() {


}

@Preview(
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ScreenContentPreview() {
    TimerTheme {
        Surface {
            ScreenContent()
        }
    }
}