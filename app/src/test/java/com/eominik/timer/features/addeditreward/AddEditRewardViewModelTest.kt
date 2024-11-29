package com.eominik.timer.features.addeditreward

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.eominik.timer.core.IconKey
import com.eominik.timer.data.TimerDatabase
import com.eominik.timer.data.TimerDatabaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddEditRewardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: AddEditRewardViewModel
    private lateinit var databaseTest: TimerDatabase

    @Before
    fun setUp() {
        savedStateHandle = SavedStateHandle()
        databaseTest = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TimerDatabase::class.java
        ).allowMainThreadQueries().build()

        viewModel = AddEditRewardViewModel(databaseTest.rewardDao() ,savedStateHandle)
    }

    @Test
    fun `test isEditMode when rewardId is NO_REWARD_ID`() {
        // Ustawienie rewardId na NO_REWARD_ID
        savedStateHandle.set(ARG_REWARD_ID, NO_REWARD_ID)

        // Sprawdzenie, czy isEditMode jest false
        assertFalse(viewModel.isEditMode)
    }

    @Test
    fun `test isEditMode when rewardId is not NO_REWARD_ID`() {
        // Ustawienie rewardId na inny identyfikator nagrody
        savedStateHandle.set(ARG_REWARD_ID, 1L)

        // Sprawdzenie, czy isEditMode jest true
        assertTrue(viewModel.isEditMode)
    }

    @Test
    fun `test rewardNameInputLiveData`() {
        // Ustawienie wartości w LiveData dla nazwy nagrody
        val rewardName = "Test Reward"
        savedStateHandle.set("rewardNameLiveData", rewardName)

        // Sprawdzenie, czy rewardNameInput zwraca poprawną wartość
        assertEquals(rewardName, viewModel.rewardNameInput.value)
    }

    @Test
    fun `test chanceInPercentInputLiveData`() {
        // Ustawienie wartości w LiveData dla szansy w procentach
        val chanceInPercent = 10
        savedStateHandle.set("chanceInPercentInputLiveData", chanceInPercent)

        // Sprawdzenie, czy chanceInPercentInput zwraca poprawną wartość
        assertEquals(chanceInPercent, viewModel.chanceInPercentInput.value)
    }

    @Test
    fun `test rewardIconKeySelectionLiveData`() {
        // Ustawienie wartości w LiveData dla klucza ikony nagrody
        val iconKey = IconKey.CAKE // Przykładowa ikona nagrody (zakładając, że taka stała istnieje)
        savedStateHandle.set("rewardIconSelectionLiveData", iconKey)

        // Sprawdzenie, czy rewardIconKeySelection zwraca poprawną wartość
        assertEquals(iconKey, viewModel.rewardIconKeySelection.value)
    }

    @Test
    fun `test rewardNameInputIsErrorLiveData default value`() {
        // Sprawdzenie domyślnej wartości dla błędu w nazwie nagrody (powinna być false)
        assertFalse(viewModel.rewardNameInputIsError.value ?: false)
    }

    @Test
    fun `test showRewardIconSelectionDialog default value`() {
        // Sprawdzenie domyślnej wartości dla dialogu wyboru ikony nagrody (powinna być false)
        assertFalse(viewModel.showRewardIconSelectionDialog.value ?: false)
    }

    @Test
    fun `test eventChannel emits events`() = runBlocking {
        // Inicjalizacja kanału zdarzeń i wysłanie zdarzenia do kanału.
        val eventChannel = Channel<AddEditRewardViewModel.AddEditRewardEvent>()

        eventChannel.send(AddEditRewardViewModel.AddEditRewardEvent.RewardCreated) // Przykład zdarzenia

        val receivedEvent = eventChannel.receiveAsFlow().toList() // Odbierz zdarzenia z kanału

        assertEquals(1, receivedEvent.size) // Sprawdź liczbę odebranych zdarzeń.
        assertEquals(AddEditRewardViewModel.AddEditRewardEvent.RewardUpdated, receivedEvent[0]) // Sprawdź odebrane zdarzenie.
    }





}