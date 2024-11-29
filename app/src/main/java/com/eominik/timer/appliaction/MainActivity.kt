package com.eominik.timer.appliaction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eominik.timer.R
import com.eominik.timer.core.theme.TimerTheme
import com.eominik.timer.features.addeditreward.ARG_REWARD_ID
import com.eominik.timer.features.addeditreward.NO_REWARD_ID
import com.eominik.timer.features.addeditreward.AddEditRewardScreen
import com.eominik.timer.features.rewardlist.RewardListScreen
import com.eominik.timer.features.timer.TimerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerTheme {
                ScreenContent()
            }
        }
    }
}


@Composable
private fun ScreenContent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar{
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavDestinations.forEach { bottomNavDestination ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                bottomNavDestination.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(stringResource(bottomNavDestination.label))
                        },
                        alwaysShowLabel = false,
                        selected = currentDestination?.hierarchy?.any { it.route == bottomNavDestination.route } == true,
                        onClick = {
                            navController.navigate(bottomNavDestination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = bottomNavDestinations[0].route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(
                route = BottomNavDestinations.TimerScreen.route
            ) {
                TimerScreen(navController)
            }
            composable(
                route = BottomNavDestinations.RewardListScreen.route
            ) {
                RewardListScreen(navController)
            }
            composable(
                route = FullScreenDestinations.AddEditRewardScreen.route + "?$ARG_REWARD_ID={$ARG_REWARD_ID}",
                arguments = listOf(navArgument(ARG_REWARD_ID) {
                    defaultValue = NO_REWARD_ID
                })
            ) {
              AddEditRewardScreen(navController)
            }
        }

    }
}

val bottomNavDestinations = listOf<BottomNavDestinations>(
    BottomNavDestinations.TimerScreen,
    BottomNavDestinations.RewardListScreen
)

sealed class BottomNavDestinations(
    val route: String,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    object TimerScreen :
        BottomNavDestinations(route = "timer",
            Icons.Outlined.Timer,
            R.string.timer)

    object RewardListScreen :
        BottomNavDestinations(route = "reward_list",
            Icons.Outlined.List,
            R.string.reward_list)
}

sealed class FullScreenDestinations(
    val route: String,
) {
    object AddEditRewardScreen : FullScreenDestinations(route = "add_edit_reward")
}


@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    TimerTheme {
        ScreenContent()
    }
}