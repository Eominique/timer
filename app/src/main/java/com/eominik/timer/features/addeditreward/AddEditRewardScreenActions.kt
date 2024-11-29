package com.eominik.timer.features.addeditreward

import com.eominik.timer.core.IconKey

interface AddEditRewardScreenActions {
    fun onRewardNameInputChanged(input: String)
    fun onChanceInPercentInputChanged(input: Int)
    fun onRewardIconButtonClicked()
    fun onRewardIconSelected(iconKey: IconKey)
    fun onRewardIconDialogDismissRequest()
    fun onSaveClicked()
}