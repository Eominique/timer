package com.eominik.timer.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eominik.timer.core.IconKey
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TimerDatabaseTest {

    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    private lateinit var database: TimerDatabase

    @Before
    fun setup() {
//        hiltRule.inject()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TimerDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetReward() = runBlocking {
        // Przygotowanie danych do testu
        val reward = Reward(
            iconKey = IconKey.CAKE,
            name = "1 piece of cake",
            chanceInPercent = 5
        )

        // Wstawienie nagrody do bazy danych
        database.rewardDao().insertReward(reward)

        // Pobranie nagrody z bazy danych
        val rewards = database.rewardDao().getAllRewards().first()

        // Sprawdzenie, czy wstawiona nagroda została poprawnie pobrana
        assertThat(rewards).containsExactly(reward)
    }

    @Test
    fun onCreateCallback_insertsInitialRewards() = runBlocking {
        // Sprawdzamy, czy domyślne nagrody zostały wstawione po utworzeniu bazy danych

        val rewards = database.rewardDao().getAllRewards().first()

        // Sprawdzenie liczby nagród i ich właściwości
        assertThat(rewards.size).isEqualTo(3) // Zakładamy, że w Callback wstawiono 3 nagrody

      //  assertThat(rewards).contains(
      //      Reward(IconKey.CAKE, "1 piece of cake", 5).toString(),
      //      Reward(IconKey.BATH_TUB, "Take a bath", 7),
      //      Reward(IconKey.TV, "Watch 1 episode of my favorite show", 10)
    //    )


    }
}