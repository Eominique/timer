package com.eominik.timer.data

import androidx.room.Room
import junit.framework.TestCase.assertEquals
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class RewardDaoTest {

    private lateinit var database: TimerDatabase // Zastąp AppDatabase swoją klasą bazy danych
    private lateinit var rewardDao: RewardDao

    @Before
    fun setup() {
        // Tworzenie in-memory bazy danych do testów
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TimerDatabase::class.java // Zastąp AppDatabase swoją klasą bazy danych
        ).build()

        rewardDao = database.rewardDao() // Uzyskanie instancji RewardDao
    }

    @After
    fun teardown() {
        database.close() // Zamknięcie bazy danych po zakończeniu testów
    }

    @Test
    fun insertReward_retrievesReward() = runBlocking {
        // Przygotowanie danych
        val reward = Reward(iconKey = "icon_star", title = "Star Reward", chanceInPercent = 50)

        // Wstawienie nagrody do bazy danych
        rewardDao.insertReward(reward)

        // Pobranie wszystkich nagród i sprawdzenie ich zawartości
        val rewards = rewardDao.getAllRewards().first() // Użycie first() do pobrania pierwszej wartości Flow

        assertEquals(1, rewards.size) // Sprawdzenie, czy rozmiar listy wynosi 1
        assertEquals(reward.iconKey, rewards[0].iconKey) // Sprawdzenie klucza ikony
        assertEquals(reward.title, rewards[0].title) // Sprawdzenie tytułu nagrody
        assertEquals(reward.chanceInPercent, rewards[0].chanceInPercent) // Sprawdzenie szansy w procentach
    }
}