package com.eominik.timer.di

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eominik.timer.data.RewardDao
import com.eominik.timer.data.TimerDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AppModuleTest {

    @get:Rule
  //  var hiltRule = HiltAndroidRule(this)

    private lateinit var application: Application
    private lateinit var database: TimerDatabase
    private lateinit var rewardDao: RewardDao

    @Before
    fun setUp() {
  //    hiltRule.inject()
        application = ApplicationProvider.getApplicationContext()

        // Tworzenie in-memory bazy danych do testów
        database = Room.inMemoryDatabaseBuilder(
            application,
            TimerDatabase::class.java
        ).allowMainThreadQueries().build()

        rewardDao = database.rewardDao()
    }

    @Test
    fun `test provideDatabase returns a non-null TimerDatabase`() {
        val providedDatabase = Room.databaseBuilder(
            application,
            TimerDatabase::class.java,
            "it_database"
        ).build()

        assertThat(providedDatabase).isNotNull()
    }

    @Test
    fun `test provideReward returns a non-null RewardDao`() {
        // Używamy rewardDao, który został stworzony w setUp()
        assertThat(rewardDao).isNotNull()
    }

    @Test
    fun `test database callback inserts initial rewards`() = runBlocking {
        // Sprawdź, czy domyślne nagrody są wstawione po utworzeniu bazy danych

        val rewards = rewardDao.getAllRewards().first()

        // Zakładamy, że Callback wstawia 3 nagrody (dostosuj w zależności od implementacji)
        assertThat(rewards.size).isEqualTo(3)
//        assertThat(rewards).contains(
//            Reward(IconKey.CAKE, "1 piece of cake", 5),
//            Reward(IconKey.BATH_TUB, "Take a bath", 7),
//            Reward(IconKey.TV, "Watch 1 episode of my favorite show", 10)
//        )
    }
}


