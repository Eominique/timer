package com.eominik.timer.features.addeditreward

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.eominik.timer.core.IconKey
import com.eominik.timer.data.RewardDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddEditRewardViewModel @Inject constructor(
    private val rewardDao: RewardDao,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), AddEditRewardScreenActions {



    override fun onRewardNameInputChanged(input: String) {
        TODO("Not yet implemented")
    }

    override fun onChanceInPercentInputChanged(input: Int) {
        TODO("Not yet implemented")
    }

    override fun onRewardIconButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun onRewardIconSelected(iconKey: IconKey) {
        TODO("Not yet implemented")
    }

    override fun onRewardIconDialogDismissRequest() {
        TODO("Not yet implemented")
    }

    override fun onSaveClicked() {
        TODO("Not yet implemented")
    }


}

const val ARG_REWARD_ID = "rewardId"
const val NO_REWARD_ID = -1L