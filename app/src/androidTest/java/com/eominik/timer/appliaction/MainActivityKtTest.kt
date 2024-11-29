package com.eominik.timer.appliaction

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class MainActivityKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInitialScreenIsTimerScreen() {
        // Ustawienie kompozycji
        composeTestRule.setContent {
            ScreenContent()
        }

        // Sprawdzenie, czy TimerScreen jest wyświetlany jako początkowy ekran
        composeTestRule.onNodeWithText("Timer").assertIsDisplayed()
    }

    @Test
    fun testNavigationToRewardListScreen() {
        // Ustawienie kompozycji
        composeTestRule.setContent {
            ScreenContent()
        }

        // Kliknięcie w NavigationBarItem do RewardListScreen
        composeTestRule.onNodeWithText("Reward List").performClick()

        // Sprawdzenie, czy RewardListScreen jest wyświetlany
        composeTestRule.onNodeWithText("Reward List").assertIsDisplayed()
    }

    @Test
    fun testNavigationToAddEditRewardScreen() {
        // Ustawienie kompozycji
        composeTestRule.setContent {
            ScreenContent()
        }

        // Kliknięcie w NavigationBarItem do RewardListScreen
        // (zakładając, że to tam jest przycisk do dodawania)
        composeTestRule.onNodeWithText("Reward List").performClick()

        // Zakładamy, że w RewardListScreen jest przycisk do dodawania nagrody
        composeTestRule.onNodeWithText("Add Reward").performClick()
        // Zmodyfikuj tekst przycisku zgodnie z Twoim UI

        // Sprawdzenie, czy AddEditRewardScreen jest wyświetlany
        composeTestRule.onNodeWithText("Add/Edit Reward").assertIsDisplayed()
    // Zmodyfikuj tekst zgodnie z Twoim UI
    }
}