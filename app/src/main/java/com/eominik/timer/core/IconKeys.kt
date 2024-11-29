package com.eominik.timer.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsFootball
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector

enum class IconKey(val rewardIcon: ImageVector) {
    STAR(Icons.Default.Star),
    CAKE(Icons.Default.Cake),
    BATH_TUB(Icons.Default.Bathtub),
    TV(Icons.Default.Tv),
    FAVORITE(Icons.Default.Favorite),
    PETS(Icons.Default.Pets),
    PHONE(Icons.Default.Phone),
    GIFT_CARD(Icons.Default.CardGiftcard),
    GAME_PAD(Icons.Default.Gamepad),
    MONEY(Icons.Default.Money),
    COMPUTER(Icons.Default.Computer),
    GROUP(Icons.Default.Group),
    HAPPY(Icons.Default.Mood),
    BEVERAGE(Icons.Default.EmojiFoodBeverage),
    MOTORBIKE(Icons.Default.SportsMotorsports),
    FOOTBALL(Icons.Default.SportsFootball),
    HEADPHONES(Icons.Default.Headphones),
    SHOPPING_CART(Icons.Default.ShoppingCart),
    BICYCLE(Icons.Default.DirectionsBike),
    PIZZA(Icons.Default.LocalPizza),

}

object IconKeys {
    const val CAKE = "CAKE"
    const val BATH_TUB = "BATH_TUB"
    const val TV = "TV"
}

val rewardIcons = mapOf<String, ImageVector>(
    Pair(IconKeys.CAKE, Icons.Default.Cake),
    Pair(IconKeys.BATH_TUB, Icons.Default.Bathtub),
    Pair(IconKeys.TV, Icons.Default.Tv)
)

val defaultIcon = Icons.Default.Star