package com.eominik.timer.features.rewardlist

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eominik.timer.R
import com.eominik.timer.appliaction.FullScreenDestinations
import com.eominik.timer.core.IconKey
import com.eominik.timer.core.ListBottomPadding
import com.eominik.timer.core.theme.TimerTheme
import com.eominik.timer.data.Reward
import com.eominik.timer.features.addeditreward.ARG_REWARD_ID
import kotlinx.coroutines.launch

@Composable
fun RewardListScreen(
    navController: NavController,
    viewModel: RewardListViewModel = hiltViewModel()
) {

    val rewards by viewModel.rewards.observeAsState(listOf())
    ScreenContent(
        rewards = rewards,
        onAddNewRewardClicked = {
            navController.navigate(FullScreenDestinations.AddEditRewardScreen.route)
        },
        onRewardItemClicked = { id ->
            navController.navigate(FullScreenDestinations.AddEditRewardScreen.route + "?$ARG_REWARD_ID=$id")
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    rewards: List<Reward>,
    onRewardItemClicked: (Long) -> Unit,
    onAddNewRewardClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(stringResource(R.string.reward_list))
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewRewardClicked,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_new_reward)
                )
            }
        },
    ) {
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        Box {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = ListBottomPadding
                ),
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(rewards) { reward ->
                    RewardItem(reward, onItemClicked = { id ->
                        onRewardItemClicked(id)
                    })
                }
            }
            AnimatedVisibility(
                visible = listState.firstVisibleItemIndex > 3,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    containerColor = Color.LightGray,
                    contentColor = Color.Black,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ExpandLess,
                        contentDescription = stringResource(R.string.scroll_to_top),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RewardItem(
    reward: Reward,
    onItemClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onItemClicked(reward.id) },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = reward.iconKey.rewardIcon,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp)
                    .fillMaxWidth()
            )
            Column() {
                Text(
                    text = reward.name,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(R.string.chance) + ": ${reward.chanceInPercent}%",
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
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
private fun RewardItemPreview() {
    TimerTheme {
        Surface {
            RewardItem(Reward("Title", 5, IconKey.BATH_TUB), onItemClicked = {})
        }
    }
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
                listOf(
                    Reward(name = "CAKE", 5, iconKey = IconKey.CAKE),
                    Reward(name = "BATH_TUB", 20, iconKey = IconKey.BATH_TUB),
                    Reward(name = "TV", 60, iconKey = IconKey.TV),
                ),
                onAddNewRewardClicked = {},
                onRewardItemClicked = {}
            )
        }
    }
}



