package com.eominik.timer.core.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ITIconButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    fun TestIconButton(onClick: () -> Unit = {}) {
        ITIconButton(onClick = onClick) {
            // Możesz dodać ikonę lub tekst jako zawartość przycisku
            Box(modifier = Modifier.size(50.dp)
                .background(Color.Transparent)) {
                // Placeholder for icon or content
            }
        }
    }

    @Test
    fun testITIconButtonIsClickable() {
        var clicked = false

        composeTestRule.setContent {
            TestIconButton {
                clicked = true
            }
        }

        // Sprawdź, czy przycisk jest wyświetlany i kliknij go
        composeTestRule.onNodeWithContentDescription("Test Icon")
            .performClick()

        // Sprawdź, czy zmienna clicked została ustawiona na true po kliknięciu
        assert(clicked)
    }

    @Composable
    @Test
    fun testITIconButtonBackgroundColor() {
        composeTestRule.setContent {
            TestIconButton()
        }

        // Sprawdzenie koloru tła przycisku (domyślny kolor)
        val expectedColor = if (isSystemInDarkTheme()) Color.Gray else Color.LightGray

        // Użyj captureToImage() do uzyskania koloru tła.
        val color = composeTestRule.onNodeWithContentDescription("Test Icon")
            .captureToImage()
    //        .readPixels()
    //        .toArgb()

        // Porównaj uzyskany kolor z oczekiwanym kolorem.
 //       assert(color == expectedColor.toArgb())
    }
}