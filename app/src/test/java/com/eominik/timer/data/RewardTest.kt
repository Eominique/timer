package com.eominik.timer.data



import org.junit.Assert.assertEquals
import org.junit.Test

class RewardTest {

    @Test
    fun testRewardCreation() {
        // Przygotowanie danych
        val iconKey = "icon_star"
        val title = "Star Reward"
        val chanceInPercent = 50

        // Utworzenie obiektu Reward
        val reward = Reward(iconKey, title, chanceInPercent)

        // Weryfikacja właściwości obiektu
        assertEquals(iconKey, reward.iconKey)
        assertEquals(title, reward.title)
        assertEquals(chanceInPercent, reward.chanceInPercent)
        assertEquals(0L, reward.id) // Sprawdzamy domyślną wartość id
    }

    @Test
    fun testRewardWithCustomId() {
        // Przygotowanie danych
        val iconKey = "icon_trophy"
        val title = "Trophy Reward"
        val chanceInPercent = 75
        val customId = 1L

        // Utworzenie obiektu Reward z niestandardowym id
        val reward = Reward(iconKey, title, chanceInPercent, customId)

        // Weryfikacja właściwości obiektu
        assertEquals(iconKey, reward.iconKey)
        assertEquals(title, reward.title)
        assertEquals(chanceInPercent, reward.chanceInPercent)
        assertEquals(customId, reward.id)
    // Sprawdzamy niestandardową wartość id


    }
}