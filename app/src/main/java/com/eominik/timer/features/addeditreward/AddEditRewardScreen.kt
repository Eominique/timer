package com.eominik.timer.features.addeditreward

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eominik.timer.R
import com.eominik.timer.core.IconKey
import com.eominik.timer.core.composables.ITIconButton
import com.eominik.timer.core.defaultRewardIconKey
import com.eominik.timer.core.exhaustive
import com.eominik.timer.core.theme.TimerTheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun AddEditRewardScreen(
    navController: NavController,
) {

    val viewModel: AddEditRewardViewModel = hiltViewModel()
    val isEditMode = viewModel.isEditMode
    val rewardNameInput by viewModel.rewardNameInput.observeAsState("")
    val rewardNameInputIsError by viewModel.rewardNameInputIsError.observeAsState(false)
    val chanceInPercentInput by viewModel.chanceInPercentInput.observeAsState(10)
    val rewardIconKeySelection
            by viewModel.rewardIconKeySelection.observeAsState(defaultRewardIconKey)
    val showRewardIconSelectionDialog
            by viewModel.showRewardIconSelectionDialog.observeAsState(false)

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                AddEditRewardViewModel.AddEditRewardEvent.RewardCreated -> navController.popBackStack()
                AddEditRewardViewModel.AddEditRewardEvent.RewardUpdated -> navController.popBackStack()
            }.exhaustive
        }
    }


    ScreenContent(
        isEditMode = isEditMode,
        rewardNameInput = rewardNameInput,
        rewardNameInputIsError = rewardNameInputIsError,
        chanceInPercentInput = chanceInPercentInput,
        rewardIconKeySelection = rewardIconKeySelection,
        showRewardIconSelectionDialog = showRewardIconSelectionDialog,
        actions = viewModel,
        onCloseClicked = { navController.popBackStack() },
    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    isEditMode: Boolean,
    rewardNameInput: String,
    rewardNameInputIsError: Boolean,
    chanceInPercentInput: Int,
    rewardIconKeySelection: IconKey,
    showRewardIconSelectionDialog: Boolean,
    actions: AddEditRewardScreenActions,
    onCloseClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            val appBarTitle =
                stringResource(if (isEditMode) R.string.edit_reward else R.string.add_reward)
            TopAppBar(
                title = {
                    Text(appBarTitle)
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClicked) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = stringResource(R.string.close)
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = actions::onSaveClicked,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = stringResource(R.string.save_reward)
                )
            }
        },
    ){

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RewardIconSelectionDialog(
    onDismissRequest: () -> Unit,
    onIconSelected: (iconKey: IconKey) -> Unit,
) {

}

@Preview(
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
private fun ScreenContentPreview() {
    TimerTheme {
        Surface {
            ScreenContent(
                isEditMode = false,
                rewardNameInput = "Example reward",
                rewardNameInputIsError = false,
                chanceInPercentInput = 10,
                onCloseClicked = {},
                showRewardIconSelectionDialog = false,
                rewardIconKeySelection = defaultRewardIconKey,
                actions = object : AddEditRewardScreenActions {
                    override fun onRewardNameInputChanged(input: String) {}
                    override fun onChanceInPercentInputChanged(input: Int) {}
                    override fun onRewardIconButtonClicked() {}
                    override fun onRewardIconSelected(iconKey: IconKey) {}
                    override fun onRewardIconDialogDismissRequest() {}
                    override fun onSaveClicked() {}
                },
            )
        }
    }
}

